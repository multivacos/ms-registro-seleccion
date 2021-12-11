package com.minera.seleccion.service.impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.minera.seleccion.entity.Formulario;
import com.minera.seleccion.repository.FormularioRepository;
import com.minera.seleccion.service.FormularioService;

@Service
public class FormularioServiceImpl implements FormularioService {

	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<Formulario> consultarFormulario() {
		List<Formulario> formulariosDataSource = StreamSupport.stream(
				this.formularioRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return formulariosDataSource;
	}
	
	@Override
	public Formulario guardarFormulario(Formulario formulario) {
		return this.formularioRepository.save(formulario);
	}

	@Override
	public Formulario actualizarFormulario(Formulario formulario) {
		try {
			sendEmailWithAttachment("jalvarez.geek@gmail.com",
					"Hola, muchas felicidades has sido elegido para una entrevista con tu Gerente de división para aspirar a un mejor puesto, en el transcurso del día se agendará la cita.",
					"Minera S.A.C - Proceso de selección",
					"C:\\Users\\\\Jonatan\\imgSpringMail\\feliz.png");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return this.formularioRepository.save(formulario);
	}

	@Override
	public void eliminarFormulario(Long id) {
		this.formularioRepository.deleteById(id);
		try {
			sendEmailWithAttachment("jalvarez.geek@gmail.com",
					"Hola, lo sentimos no llegaste a calificar para el proceso de selección, ánimo sigue adelante!",
					"Minera S.A.C - Proceso de selección",
					"C:\\Users\\\\Jonatan\\imgSpringMail\\triste.png");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("spring.email.from@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);

		mailSender.send(mimeMessage);
		System.out.println("Mail Send...");
	}
}
