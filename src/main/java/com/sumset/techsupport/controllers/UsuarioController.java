/**
 * 
 */
package com.sumset.techsupport.controllers;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.techsupport.models.Empresa;
import com.sumset.techsupport.models.Usuarios;
import com.sumset.techsupport.services.UsuarioService;


/**
 * @author Agustín Palomino Pardo
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/home/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value = "obtenertodos", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Usuarios>> obtenerUsuarios() throws Exception {
		ArrayList<Usuarios> usuarios = usuarioService.obtenerTodosUsuarios();
		return ResponseEntity.ok(usuarios);
	}

	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> guardarUsuario(@RequestBody Usuarios usuario) throws Exception {
		System.out.println("Usuario: "+usuario.toString());
		Usuarios usr = usuarioService.guardarUsuario(usuario);
		return ResponseEntity.ok(usr);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Usuarios> usr = usuarioService.obtenerUsuarioPorId(id);
		if (usr.isPresent()) {
			return ResponseEntity.ok(usr.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "borrar/{id}", method = RequestMethod.POST)
	public String eliminarUsuario(@PathVariable("id") Long id) throws Exception {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se eliminó el usuario con id: " + id;
		} else {
			return "No se pudo eliminar el usuario con id: " + id;
		}
	}

	@RequestMapping(value = "buscarpormail/{mail}", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> obtenerUsuarioPorMail(@PathVariable("mail") String mail) throws Exception {
		Usuarios usr = usuarioService.obtenerUsuarioPorMail(mail);
		if (usr != null) {
			return ResponseEntity.ok(usr);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "buscartecnicos", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Usuarios>> buscarTecnicos() throws Exception {
		ArrayList<Usuarios> usuarios = usuarioService.buscarTecnicos();
		return ResponseEntity.ok(usuarios);
	}

	@RequestMapping(value = "autenticar", method = RequestMethod.POST)
	public ResponseEntity<Usuarios> autenticarUsuario(@RequestBody Usuarios usuario)
			throws Exception, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		String secret = "a1b2c3d4e5";
		String cipherText = usuario.getUsrClave();
		byte[] cipherData = Base64.getDecoder().decode(cipherText);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
		SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
		IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

		byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
		Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedData = aesCBC.doFinal(encrypted);
		String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

		usuario.setUsrClave(decryptedText);
		// ****
		
		Usuarios lstUsuarios = usuarioService.autenticarUsuario(usuario.getUsrMail(), usuario.getUsrClave());
		if (lstUsuarios != null) {
			return ResponseEntity.ok(lstUsuarios);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password,
			MessageDigest md) {

		int digestLength = md.getDigestLength();
		int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
		byte[] generatedData = new byte[requiredLength];
		int generatedLength = 0;

		try {
			md.reset();

			// Repeat process until sufficient data has been generated
			while (generatedLength < keyLength + ivLength) {

				// Digest data (last digest if available, password data, salt if available)
				if (generatedLength > 0)
					md.update(generatedData, generatedLength - digestLength, digestLength);
				md.update(password);
				if (salt != null)
					md.update(salt, 0, 8);
				md.digest(generatedData, generatedLength, digestLength);

				// additional rounds
				for (int i = 1; i < iterations; i++) {
					md.update(generatedData, generatedLength, digestLength);
					md.digest(generatedData, generatedLength, digestLength);
				}

				generatedLength += digestLength;
			}

			// Copy key and IV into separate byte arrays
			byte[][] result = new byte[2][];
			result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
			if (ivLength > 0)
				result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

			return result;

		} catch (DigestException e) {
			throw new RuntimeException(e);

		} finally {
			// Clean out temporary data
			Arrays.fill(generatedData, (byte) 0);
		}
	}

}
