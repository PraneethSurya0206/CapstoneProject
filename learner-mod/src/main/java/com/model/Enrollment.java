package com.model;

import java.time.LocalDate;

import com.MainAppLearner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "enrollments")
public class Enrollment {
	
	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "enrollment_id")
	    private int enrollmentId;

	    @Column(name = "academy_id" ,nullable = false)
	    private int academyId;

	    @Column(name = "enrollment_date")
	    private LocalDate enrollmentDate;
	    
	    @Column(name= "learner_id")
	    private int learnerId;

		public int getEnrollmentId() {
			return enrollmentId;
		}

		public void setEnrollmentId(int enrollmentId) {
			this.enrollmentId = enrollmentId;
		}

		public int getAcademyId() {
			return academyId;
		}

		public void setAcademyId(int academyId) {
			this.academyId = academyId;
		}

		public LocalDate getEnrollmentDate() {
			return enrollmentDate;
		}

		public void setEnrollmentDate(LocalDate enrollmentDate) {
			this.enrollmentDate = enrollmentDate;
		}

		

		public int getLearnerId() {
			return learnerId;
		}

		public void setLearnerId(int learnerId) {
			this.learnerId = learnerId;
		}

		public Enrollment() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Enrollment(int academyId, LocalDate enrollmentDate, int learnerId) {
			super();
			this.academyId = academyId;
			this.enrollmentDate = enrollmentDate;
			this.learnerId = learnerId;
		}

		@Override
		public String toString() {
			return "Enrollment [enrollmentId=" + enrollmentId + ", academyId=" + academyId + ", enrollmentDate="
					+ enrollmentDate + ", learnerId=" + learnerId + "]";
		}
		

}
	   /* @ManyToOne
		@JoinColumn(name = "learnerId", nullable = false)
	    private Learner learner;*/
