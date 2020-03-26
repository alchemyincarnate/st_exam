# st_exam

ID: st_exam v0.1
Author: Hans Chua
This is a project for Singtel Gaming exam

section_01 is an RSSFeed UI
- app01 is the frontend component, and written in react/js
- app02 is the backend component that fetches the rss feed, and written in Java

section_02 is a localized string loader
- the app fetches from a hardcoded url in the app [https://jsonblob.com/2f378791-6c60-11ea-9610-89cf4e8bc64f] and displays the translation if available
- if the translation is unavailable, it checks for the KEY in strings.xml