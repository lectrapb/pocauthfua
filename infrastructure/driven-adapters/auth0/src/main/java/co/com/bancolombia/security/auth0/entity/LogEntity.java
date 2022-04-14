package co.com.bancolombia.security.auth0.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
public class LogEntity implements Serializable {


   @Id
   @Column(name = "log_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "log_message_id", length = 128)
   private String messageId;
   @Column(name = "log_app_id", length = 128)
   private String appId;
   @Column(name = "log_action", length = 128)
   private String action;
   @Column(name = "log_status", length = 128)
   private String status;
   @Column(name = "log_data",  length = 1600)
   private String data;
   @Column(name = "log_create", columnDefinition = "TIMESTAMP")
   private LocalDateTime create;

   @PrePersist
   private void prePersist(){

      this.create = LocalDateTime.now();
   }

   private static final long serialVersionUID = 4467257687237758360L;
}
