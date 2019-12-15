create database school2018;

use school2018;

create table students (stud_id int not null auto_increment,
					 stud_name varchar(60) not null, #student name
					 stud_place varchar(30), #student address (the place part of it)
					 stud_dt_enr date not null, #student date of enrollment
					 stud_gender char(1) not null, #student gender
					 stud_email varchar(30), #student email
					 stud_cour_id int,
					 primary key (stud_id));
		     		     
create table courses (cour_id int not null auto_increment,
					 cour_name varchar(40) not null, #course name
					 primary key (cour_id));
		           
create table departments (dep_id int not null auto_increment,
						    dep_name varchar(60) not null, #department name
							dep_initials char(3) not null, #department initials
							primary key (dep_id));	     
		           
create table classes (clas_id int not null auto_increment,
						  clas_name varchar(40) not null, #class name
					      clas_credits tinyint not null, #class credits (ECTS)
						  clas_dep_id int,
						  primary key (clas_id));
		    
create table studyplans (plan_cour_id int not null,
						   plan_clas_id int not null,
						   plan_semester tinyint not null,
						   primary key (plan_cour_id, plan_clas_id));		
		            		                 		     
create table enrollment (enr_id int not null auto_increment,
						 enr_stud_id int not null,
						 enr_plan_cour_id int not null,
						 enr_plan_clas_id int not null,
						 enr_dt_enrollment date not null, #date of enrollment
						 enr_dt_grading date, #date of grading
						 enr_grade decimal(4,2),
						 primary key (enr_id));

# Chaves estrangeiras
alter table students add constraint students_fk_courses
            foreign key (stud_cour_id) references courses(cour_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;
            
alter table classes add constraint classes_fk_departments
            foreign key (clas_dep_id) references departments(dep_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;
            
alter table studyplans add constraint studyplans_fk_courses
            foreign key (plan_cour_id) references courses(cour_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;            
            
alter table studyplans add constraint studyplans_fk_classes
            foreign key (plan_cour_id) references classes(clas_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;            

alter table enrollment add constraint enrollment_fk_students
            foreign key (enr_stud_id) references students(stud_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION; 
                        
alter table enrollment add constraint enrollment_fk_studyplans
            foreign key (enr_plan_cour_id,enr_plan_clas_id) references studyplans(plan_cour_id,plan_clas_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION; 

