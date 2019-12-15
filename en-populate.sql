-- Informação
insert into courses (cour_name) values('Engenharia Biomédica');
insert into courses (cour_name) values('Engenharia Informática');
insert into courses (cour_name) values('Engenharia Electrónica e Computadores');
insert into courses (cour_name) values('Engenharia do Ambiente');
                        
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Joaquim Pires Lopes','lisboa',str_to_date('1995.01.01','%Y.%m.%d'),'M','jpl@gmail.com',2);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Ana Maria Fonseca','Setúbal',str_to_date('1997.03.03','%Y.%m.%d'),'F','ana@sapo.pt',1);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Paula Antunes','Lisboa',str_to_date('1996.07.06','%Y.%m.%d'),'F',null,2);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Joana Ramalho Silva','Costa da Caparica',str_to_date('1984.09.23','%Y.%m.%d'),'F','juana@mac.com',3);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Rui Manuel Silva','Cascais',str_to_date('1994.08.15','%Y.%m.%d'),'M','rms@gmail.com',1);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('João Paulo Santos','Lisboa',str_to_date('1991.11.16','%Y.%m.%d'),'M','jps@yahoo.com',1);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Cristina Fernandes Lopes','Lisboa',str_to_date('1996.01.07','%Y.%m.%d'),'F',null,1);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Miguel Pinto Leite','Cascais',str_to_date('1994.01.07','%Y.%m.%d'),'M','mpl@zmail.com',3);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Francisco Costa Rosa',null,str_to_date('1998.02.16','%Y.%m.%d'),'M',null,4);  
insert into students (stud_name, stud_place, stud_dt_enr, stud_gender, stud_email, stud_cour_id) values ('Elsa Fialho Pinto','Palmela',str_to_date('1999.10.29','%Y.%m.%d'),'F','elsafi@sapo.pt',1);  
                        
insert into departments (dep_name, dep_initials) values ('Departamento de Sistemas e Informática','DSI');
insert into departments (dep_name, dep_initials) values ('Departamento de Engenharia Electrotócnica', 'DEE');
insert into departments (dep_name, dep_initials) values ('Departamento de Matemática','DEM');    

insert into classes (clas_name, clas_credits, clas_dep_id) values ('Introdução a Programação',4,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Inteligência Artificial',3,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Redes de Computadores',2,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Base de Dados',6,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Complementos de Base de Dados',2,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Análise de Sistemas',1,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Sistemas Distribuídos',1,1);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Sistemas Digitais',3,2);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Microprocessadores',2 ,2);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Electrónica Geral',3,2);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Análise Matemática I',4,3);
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Análise Matemática II',4,3); 
insert into classes (clas_name, clas_credits, clas_dep_id) values ('Aplicaçõeses Multimédia',6,1); 


insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,1,1); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,2,4); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,3,4); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,4,3); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,5,5); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,6,2); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,7,5); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,8,6); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (1,11,1);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,1,1); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,4,5); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,8,3); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,9,2); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,10,1);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (2,11,3);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (3,1,3); 
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (3,11,4);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (3,12,5);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (4,1,3);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (4,4,4);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (4,11,4);
insert into studyplans (plan_cour_id, plan_clas_id, plan_semester) values (4,12,5);

insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (1,2,1,str_to_date('2014.09.03','%Y.%m.%d'),str_to_date('2015.02.11','%Y.%m.%d'),10);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (1,2,8,str_to_date('2014.09.05','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (1,2,9,str_to_date('2015.10.06','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (2,1,1,str_to_date('2010.09.23','%Y.%m.%d'),str_to_date('2011.02.11','%Y.%m.%d'),11);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (2,1,11,str_to_date('2010.09.23','%Y.%m.%d'),str_to_date('2011.03.12','%Y.%m.%d'),13);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (2,1,4,str_to_date('2013.09.05','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (3,2,1,str_to_date('2014.10.05','%Y.%m.%d'),str_to_date('2015.02.11','%Y.%m.%d'),12);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (3,2,9,str_to_date('2014.09.06','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (4,3,12,str_to_date('2013.10.05','%Y.%m.%d'),str_to_date('2015.03.11','%Y.%m.%d'),10);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (5,1,1,str_to_date('2014.09.07','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (6,1,1,str_to_date('2013.09.23','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (6,1,11,str_to_date('2013.09.23','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (7,1,1,str_to_date('2011.09.08','%Y.%m.%d'),str_to_date('2012.02.10','%Y.%m.%d'),13);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (7,1,11,str_to_date('2011.09.08','%Y.%m.%d'),str_to_date('2012.03.07','%Y.%m.%d'),17);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (7,1,4,str_to_date('2012.09.05','%Y.%m.%d'),str_to_date('2013.07.12','%Y.%m.%d'),15);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (7,1,2,str_to_date('2012.09.05','%Y.%m.%d'),str_to_date('2013.03.11','%Y.%m.%d'),13);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (8,3,12,str_to_date('2014.10.11','%Y.%m.%d'),str_to_date('2015.03.12','%Y.%m.%d'),14);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (9,4,1,str_to_date('2013.10.07','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (9,4,11,str_to_date('2013.10.07','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (9,4,12,str_to_date('2013.10.07','%Y.%m.%d'),null,null);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (10,1,11,str_to_date('2012.09.11','%Y.%m.%d'),str_to_date('2013.02.12','%Y.%m.%d'),15);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (10,1,1,str_to_date('2013.09.12','%Y.%m.%d'),str_to_date('2014.02.11','%Y.%m.%d'),12);
insert into enrollment (enr_stud_id, enr_plan_cour_id, enr_plan_clas_id, enr_dt_enrollment, enr_dt_grading, enr_grade) values (9,4,4,str_to_date('2013.09.12','%Y.%m.%d'),str_to_date('2014.02.11','%Y.%m.%d'),12);

commit;
          