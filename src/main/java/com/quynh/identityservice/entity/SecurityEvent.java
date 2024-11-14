package com.quynh.identityservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "security_event")
public class SecurityEvent {

    public SecurityEvent(SecurityAction action, String subject, String object, String path) {
		super();
		this.action = action;
		this.subject = subject;
		this.object = object;
		this.path = path;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_event_id", nullable = false)
    Long id;

    @Column(name = "date", nullable = false)
    LocalDateTime date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    SecurityAction action;

    @Column(name = "subject", nullable = false)
    String subject;

    @Column(name = "object", nullable = false)
    String object;

    @Column(name = "path", nullable = false)
    String path;



}
