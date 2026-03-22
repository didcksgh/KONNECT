# KONNECT
Interest-Based Social Networking App with Real-Time Messaging

관심 기반 소셜 네트워킹 및 실시간 메시징 애플리케이션

## Overview | 프로젝트 개요

KONNECT is a mobile social networking platform designed to connect users based on shared interests and enable real-time communication.

KONNECT는 사용자 간 관심사를 기반으로 연결을 형성하고 실시간 커뮤니케이션을 지원하는 모바일 소셜 네트워킹 플랫폼입니다.

The system integrates an Android client with a Spring Boot backend to support user discovery, social interactions, and real-time messaging via WebSocket.

Android 클라이언트와 Spring Boot 백엔드를 연동하여 사용자 탐색, 소셜 기능 및 WebSocket 기반 실시간 메시징을 구현했습니다.

My primary contribution focused on Android client architecture and real-time communication integration, while also contributing to backend development in the user domain.

본 프로젝트에서 Android 클라이언트 아키텍처 및 실시간 통신 기능 구현을 주도했으며, 사용자 도메인 중심의 백엔드 개발에도 협업 형태로 참여했습니다.

## My Role | 담당 역할
Frontend (Primary Responsibility)

프론트엔드 (주요 담당)

•Designed and implemented Android client architecture
→ Android 클라이언트 구조 설계 및 구현

•Developed real-time messaging using WebSocket
→ WebSocket 기반 실시간 메시징 기능 개발

•Implemented REST API communication via Volley
→ Volley를 활용한 REST API 통신 구현

•Designed global WebSocket connection management (Singleton)
→ Singleton 패턴 기반 전역 WebSocket 연결 관리 구조 설계

•Implemented social interaction flows (matching, friend requests, chat UI)
→ 매칭, 친구 요청, 채팅 UI 등 소셜 상호작용 흐름 구현

•Managed asynchronous networking and UI synchronization
→ 비동기 네트워크 이벤트 처리 및 UI 상태 동기화 구현

•Built profile management and media upload UI
→ 프로필 관리 및 미디어 업로드 UI 구현

Backend (Collaborative Contribution)

백엔드 (협업 기여)

•Participated in user domain logic implementation
→ 사용자 도메인 로직 개발 일부 참여

•Contributed to REST endpoint development
→ 사용자 관리 REST API 개발 협업

•Assisted in mobile-backend data workflow design
→ 모바일-백엔드 데이터 흐름 설계 지원

•Supported frontend-backend integration testing
→ 프론트엔드-백엔드 통합 테스트 지원

## System Architecture | 시스템 구조

Android Client (Primary Contribution)
↓ REST API + WebSocket
Spring Boot Backend
↓
MySQL Database

## Tech Stack | 기술 스택
Mobile Client

•Android (Java)

•Volley

•Java-WebSocket

•Android Lifecycle Components

•SharedPreferences

Backend

•Spring Boot

•Spring WebSocket

•JPA

•MySQL

## Key Features | 주요 기능
Real-Time Messaging | 실시간 메시징

•Persistent WebSocket connection
→ 지속적인 WebSocket 연결 관리

•Direct user messaging
→ 사용자 간 1:1 실시간 메시징

•Event-driven UI updates
→ 이벤트 기반 UI 상태 업데이트

Social Interaction | 소셜 기능

•Interest-based matching
→ 관심사 기반 사용자 매칭

•Friend request workflow
→ 친구 요청 및 수락 흐름 구현

•Group interaction UI
→ 그룹 상호작용 인터페이스 구현

User Management | 사용자 관리

•Registration and login
→ 회원가입 및 로그인 기능

•Profile editing and media upload
→ 프로필 수정 및 이미지 업로드

•Backend user API collaboration
→ 사용자 도메인 API 개발 협업

## Engineering Challenges | 기술적 도전

•Handling concurrent REST + WebSocket communication
→ 모바일 환경에서 REST와 WebSocket 동시 통신 구조 설계

•Designing scalable real-time messaging architecture
→ 확장 가능한 실시간 메시징 구조 설계

•Maintaining UI consistency under async updates
→ 비동기 상태 변화 환경에서 UI 일관성 유지

•Integrating frontend workflows with backend domain logic
→ 사용자 흐름과 백엔드 도메인 로직 통합 설계

## Learning Outcomes | 핵심 성장 경험

•Real-time mobile system architecture design

•Distributed communication (REST + WebSocket)

•Cross-layer collaboration (frontend ↔ backend)

•Scalable social interaction system design

→ 실시간 모바일 시스템 아키텍처 설계 경험
→ 분산 통신 구조 설계 경험
→ 프론트엔드-백엔드 협업 기반 개발 경험
→ 확장 가능한 사용자 상호작용 시스템 설계 역량 확보

## Future Improvements | 향후 개선 방향

•Push notification integration

•Message persistence layer

•Real-time presence system

•Backend scalability improvements

•Cloud deployment

→ 푸시 알림 기능 추가
→ 메시지 저장 구조 도입
→ 실시간 접속 상태 시스템 구현
→ 백엔드 확장성 개선
→ 클라우드 배포 구조 설계

## Author
Chanho Yang