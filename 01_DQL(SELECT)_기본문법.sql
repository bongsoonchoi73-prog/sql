-- PATIENT 테이블에서 모든 컬럼 조회
SELECT * FROM PATIENT; -- 성능에 영향을 까지므로 실무 사용 금지

--
SELECT patirnt_name 
FROM patient;

--------------------
SELECT DEPT_ID, DEPT_NAME
FROM DEPT;

-------------
SELECT WARD_NAME
FROM WARD;

--------------
SELECT DOCTOR_NAME, HIRE_DATE
FROM DOCTOR;

--------------------------
SELECT PATIENT_NAME, GENDER
FROM PATIENT;

-------------------------
SELECT BIRTH_DATE, PATIENT_NAME
FROM PATIENT;

----------------------------------------------
--반복숙달
--PATIRNT 테이블 환자명,키 조회
SELECT PATIENT_NAME
     , HEIGHT_CM
     , HEIGHT_CM / 100
FROM PATIENT;

--PATIENT 테이블 환자명, 몸무게
SELECT
       PATIEMT_NAME
      ,WEIGHT_KG
      ,WEIGHT_KG / ((HEIGHT_CM / 100) * (HEIGHT_CM / 100))
FROM  PATIENT;

--> 산술연산 과정 NULL = 결과 NULL
SELECT PATIENT_NAME , ADMISSION_DATE, SYSDATE - ADMISSION_DATE
FROM PATIENT;
--------------------------
SELECT HEIGHT_CM AS "키(CM)"
       , 'CM'
FROM PATIENT;

-------------------------
SELECT DISTINCT BLOOD_TYPE
               ,PATIENT_NAME
               , '형'
FROM PATIENT;

------------------------------- 80KG 이상인 사람
SELECT PATIENT_NAME
      , WEIGHT_KG
FROM PATIENT
WHERE WEIGHT_KG >= 80;

----------------------------
SELECT PATIENT_NAME
      , GENDER
FROM PATIENT
WHERE GENDER = 'M';

-------------------------------
SELECT PATIENT_NAME
       ,WARD_ID
       ,HEIGHT_CM
FROM PATIENT
WHERE WARD_ID = 'W3'
   --AND
      OR
      HEIGHT_CM >= 170;

--------------------------------
SELECT PATIENT_NAME
      ,HEIGHT_CM
FROM PATIENT
WHERE HEIGHT_CM >= 160
     AND
    HEIGHT_CM  <= 170;

----------------------------------
SELECT PATIENT_NAME
       , HEIGHT_CM
FROM PATIENT
WHERE HEIGHT_CM BETWEEN 160 AND 170;

-------------------------------------

SELECT PATIENT_NAME
       , BIRTH_DATE
FROM PATIENT
WHERE BIRTH_DATE BETWEEN TO_DATE('1990-01-01', 'YYYY-MM-DD')
                      AND TO_DATE('1999-12-31', 'YYYY-MM-DD');

-----------------------------
SELECT PATIENT_NAME
      , DIAGNOSIS
 FROM PATIENT
WHERE DIAGNOSIS LIKE '%골절';

-------------------------
SELECT DOCTOR_NAME
FROM DOCTOR
WHERE DOCTOR_NAME LIKE  '__';

------------------------------------
SELECT PATIENT_NAME
      ,DISCHARGE_DATE
FROM PATIENT
WHERE
      --DISCHAGE_DATE = NULL; NULL은 동등비교로 불가능
      -- DISCHARGE_DATE IS NULL;
      DISCHARGE_DATE IS NOT NULL;
---------------------------------
SELECT 
       PATIENT_NAME || '의 담당의 코드는' || DOCTOR_ID || '입니다.' 별칭
FROM 
      PATIENT;
---------------------------------------------
SELECT PATIENT_NAME
      , BLOOD_TYPE
FROM PATIENT
WHERE 
   BLOOD_TYPE = 'A'
 OR
  BLOOD_TYPE = 'B'
 OR 
  BLOOD_TYPE = '0';
----------------------------------------
SELECT PATIENT_NAME, BLOOD_TYPE
FROM PATIENT
WHERE BLOOD_TYPE NOT IN ('A', 'B', 'C');

------------------------------------
SELECT PATIENT_NAME,WARD_ID,GENDER,WEIGHT_KG
FROM PATIENT
WHERE WARD_ID = 'W3'
  AND  
      GENDER ='M'
 OR 
    WEIGHT_KG >= 85;
-----------------------------------------
SELECT * FROM PATIENT;
-- ORDER BY
SELECT PATIENT_NAME
      ,HEIGHT_CM
FROM 
       PATIENT
ORDER
BY 
   --HEIGHT_CM;
   HEIGHT_CM DESC;
---------------------------
SELECT
   PATIENT_NAME
   ,ADMISSION_DATE
  FROM 
    PATIENT
  ORDER 
    BY 
      ADMISSION_DATE DESC;
-----------
SELECT 
       PATIENT_NAME
     , DISCHARGE_DATE
  FROM 
       PATIENT
  ORDER 
     BY 
        DISCHARGE_DATE DESC NULLS LAST;
--
SELECT PATIENT_NAME
      , HEIGHT_CM / 100 키
      
   FROM PATIENT
   WHERE HEIGHT_CM / 100 >= 1.5
   ORDER BY --키 DESC;
   2 DESC; --비추
   ---------------------
   SELECT PATIENT_NAME
         , WARD_ID
   FROM PATIENT 
   ORDER BY WARD_ID
            , PATIENT_NAME;
         
        