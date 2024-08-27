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
	    @Column(name = "enrollmentID")
	    private int enrollment_id;

	    @Column(name = "academyID" ,nullable = false)
	    private int academy_id;

	    @Column(name = "enrollmentDate")
	    private LocalDate enrollment_date;

	    
	    @ManyToOne
		@JoinColumn(name = "learnerId", nullable = false)
	    private Learner learner;

		public int getEnrollment_id() {
			return enrollment_id;
		}

		public void setEnrollment_id(int enrollment_id) {
			this.enrollment_id = enrollment_id;
		}

		public int getAcademy_id() {
			return academy_id;
		}

		public void setAcademy_id(int academy_id) {
			this.academy_id = academy_id;
		}

		public LocalDate getEnrollment_date() {
			return enrollment_date;
		}

		public void setEnrollment_date(LocalDate enrollment_date) {
			this.enrollment_date = enrollment_date;
		}

		public Learner getLearner_id() {
			return learner;
		}

		public void setLearner_id(Learner learner_id) {
			this.learner = learner_id;
		}

		public Enrollment() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Enrollment(int academy_id, LocalDate enrollment_date, Learner learner_id) {
			super();
			this.academy_id = academy_id;
			this.enrollment_date = enrollment_date;
			this.learner = learner_id;
		}

		@Override
		public String toString() {
			return "Enrollment [enrollment_id=" + enrollment_id + ", academy_id=" + academy_id + ", enrollment_date="
					+ enrollment_date + ", learner_id=" + learner + "]";
		}
		

}
