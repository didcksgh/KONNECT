KONNECT – Interest-Based Social Networking App with Real-Time Messaging

Overview

KONNECT is a mobile social networking platform designed to connect users based on shared interests and enable real-time communication.

The system integrates an Android client with a Spring Boot backend to support user discovery, friend interactions, group features, and real-time direct messaging using WebSocket.

My primary contribution focused on the Android client architecture and real-time communication integration, while also contributing to backend development within the user management domain.

My Role

-Frontend (Primary Responsibility)

• Designed and implemented the Android client application

• Developed real-time messaging interface using WebSocket

• Implemented REST API communication using Volley

• Designed application-wide WebSocket connection management using Singleton pattern

• Implemented social interaction flows (matching, friend requests, chat UI)

• Managed asynchronous network events and UI state synchronization

• Built profile management and media upload UI

-Backend (Collaborative Contribution)

• Participated in the implementation of User domain logic

• Contributed to REST endpoint development for user management

• Assisted in designing data interaction workflows between mobile client and backend

• Supported integration testing between frontend and backend systems

System Architecture

Android Client (Primary Contribution)
      │
REST API + WebSocket
      │
Spring Boot Backend
      │
MySQL Database

Tech Stack

-Mobile Client

• Android (Java)

• Volley HTTP networking

• Java-WebSocket library

• Android Lifecycle components

• SharedPreferences

-Backend (Collaborative Scope)

• Spring Boot

• Spring WebSocket

• JPA

• MySQL

Key Features

-Real-Time Messaging

• Persistent WebSocket connection handling

• Direct user-to-user messaging

• Event-driven UI updates

-Social Interaction

• Interest-based matching interface

• Friend request workflows

• Group interaction UI

-User Management

• User registration and login

• Profile editing and media upload

• Backend user domain API collaboration

Engineering Challenges

• Managing concurrent REST and WebSocket communication in a mobile environment

• Designing scalable real-time messaging architecture on Android

• Maintaining UI consistency across asynchronous state updates

• Integrating frontend user workflows with backend domain logic

Learning Outcomes

• Real-time mobile communication architecture design

• REST and WebSocket integration in distributed systems

• Cross-layer collaboration between frontend and backend components

• Designing scalable user interaction systems

Future Improvements

• Push notification integration

• Message persistence layer

• Real-time presence system

• Backend scalability improvements

• Cloud deployment

Author

Chanho Yang
Mobile-Focused Software Engineer
Interest in Real-Time Systems and Full-Stack Architecture