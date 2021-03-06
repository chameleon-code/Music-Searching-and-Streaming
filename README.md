# Music-Searching-and-Streaming
### Spring MVC framework Project by Gradle build - 음악 검색 및 스트리밍 서비스

## Table of Contents
 1. Features
 2. Development Environment
 3. Preview Video
 4. Download PDF file 
 5. Basic Architecture

 

### 1. Features
 - Localhost에 있는 음악파일을 파싱하여 DB에 저장. (https://github.com/kinayoon/MetadataExtractor)
  - 해당 DB를 토대로 현 웹 어플리케이션 개발.


 - 주요 기능
  - 검색
    - 아티스트명/곡명/앨범명으로 모두 검색 가능 
    - 메인화면 검색 > 검색결과 화면에서 데이터의 일부분 출력   
    - 검색결과 화면에서 '더보기'링크 클릭 시, 검색어에 따른 모든 데이터 출력 (pagination으로 분류)
    - 검색결과 화면에서 아티스트명/앨범명 클릭 시, 해당되는 모든 곡 출력

  - 회원가입 및 로그인
    - '마이뮤직'페이지 회원전용 (좋아하는 곡을 따로 담아 재생리스트를 만들 수 있음)
    - 해당 곡의 '좋아요' 버튼 클릭 시, '마이뮤직' 페이지에서 곡 추가 생성
    
  - 음악 스트리밍
    - 전체 재생 및 전체 삭제, 한 곡 듣기, 선택 담기를 통해 현재 재생리스트에 곡 추가 가능    

### 2. Development Environment
 - IDE : STS (Spring Tool Suite) 3.8.1.RELEASE
 - WAS : Tomcat 7.0
 - OS : Window7 (64 bit)
 - Language : Java 1.8, HTML, CSS, Javascript, JQuery, Ajax
 - DB : MySQL 5.1.60
 - Persistence Framework : MyBatis 
 
### 3. Preview Video    
  - [Follow me! ----> Youtube](https://youtu.be/PJmvVzF63wY)
  
### 4. Download PDF file
  - [Follwe me! ----> For more information, see!](https://github.com/kinayoon/Music-Searching-and-Streaming/files/648239/spring.project.music_streaming.pdf)


### 5. Basic Architecture
   ![basic](https://cloud.githubusercontent.com/assets/21224368/20875725/4f7bcf12-bb00-11e6-92c1-1591b5e62fd3.JPG)

