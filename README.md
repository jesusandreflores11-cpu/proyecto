# 🍽️ Proyecto: Gestión de Tareas en Restaurante
![Java](https://img.shields.io/badge/Java-17-blue) 
![Status](https://img.shields.io/badge/Status-Avance%20Proyecto-success) 
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

**Materia:** Estructura de Datos  
**Maestra:** Blanca Aracely Aranda Machorro  
**Fecha:** 31/08/2025  

---

## 📑 Índice
1. [Alcance](#1-alcance)  
2. [Análisis](#2-análisis)  
   - [Situación problema](#situación-problema)  
   - [Justificación](#justificación)  
   - [Requerimientos funcionales](#requerimientos-funcionales)  
   - [Requerimientos no funcionales](#requerimientos-no-funcionales)  
3. [Diseño](#3-diseño)  
   - [Clases principales](#clases-principales)  
   - [Diagrama conceptual](#diagrama-conceptual)  
   - [Interfaz de usuario](#interfaz-de-usuario)  
4. [Desarrollo](#4-desarrollo)  
5. [Implementación](#5-implementación)  
   - [Entorno de ejecución](#entorno-de-ejecución)  
   - [Pasos de compilación y ejecución](#pasos-de-compilación-y-ejecución)  
6. [Pruebas](#6-pruebas)  
   - [Casos de prueba](#casos-de-prueba)  
   - [Resultados de las pruebas](#resultados-de-las-pruebas)  
7. [Resultados de pruebas](#7-resultados-de-pruebas)  
8. [Glosario de términos](#8-glosario-de-términos)  
9. [Conclusión](#9-conclusión)  
10. [Pasos del proyecto](#10-pasos-del-proyecto)  
11. [Rúbrica de evaluación](#11-rúbrica-de-evaluación)  

---

## 1. Alcance
El proyecto busca proporcionar una solución sencilla y funcional para la **gestión de tareas en un restaurante**.  

Funciones principales:
- Registrar tareas según el nivel de urgencia.  
- Asignar tareas a diferentes departamentos (cocina, meseros, caja, etc.).  
- Visualizar todas las tareas pendientes en un formato estructurado.  
- Atender las tareas según la prioridad definida:  
  - **Urgentes:** mediante una pila (LIFO).  
  - **Programadas:** mediante una cola (FIFO).  
  - **Departamentales:** mediante una lista.  

📌 **Nota:** Se limita a una **interfaz por consola** con menús de texto interactivos.  

---

## 2. Análisis

### Situación problema
En un restaurante existen múltiples tareas simultáneas: preparación de platillos, atención a clientes, cobros en caja y organización del personal.  
Si no se gestionan con orden, pueden acumularse, olvidarse o atenderse en un orden ineficiente.  

### Justificación
La elección de **estructuras de datos (pila, cola, lista)** responde a la necesidad de organizar tareas según su naturaleza:  
- **Pila (LIFO):** para emergencias o actividades críticas.  
- **Cola (FIFO):** para secuencia de actividades programadas.  
- **Lista:** flexible para clasificar tareas por departamento.  

### Requerimientos funcionales
1. Registrar una nueva tarea (descripción, departamento, urgencia).  
2. Guardar la tarea en la estructura correspondiente.  
3. Mostrar todas las tareas clasificadas.  
4. Atender la tarea urgente más reciente (pila).  
5. Atender la tarea programada más antigua (cola).  
6. Eliminar tareas de la lista por descripción.  

### Requerimientos no funcionales
- Lenguaje: **Java**  
- Ejecución: **consola**  
- Organización modular con paquetes (`estructuras`, `ui`)  

---

## 3. Diseño

### Clases principales
- `Tarea`: contiene la descripción, departamento y nivel de urgencia.  
- `PilaTareas`: administra las tareas urgentes (Stack).  
- `ColaTareas`: administra las tareas programadas (Queue).  
- `ListaTareas`: administra las tareas por departamento (ArrayList).  
- `ConsolaUI`: provee el menú interactivo.  
- `RestauranteApp`: inicia el sistema.  

### Diagrama conceptual
RestauranteApp → inicia ConsolaUI
ConsolaUI → gestiona la interacción y distribuye las tareas
PilaTareas / ColaTareas / ListaTareas → almacenan y gestionan
Tarea → clase base

shell
Copiar código

### Interfaz de usuario
Ejemplo de menú:  
Restaurante La Buena Mesa

Agregar tarea urgente (PILA)

Agregar tarea programada (COLA)

Agregar tarea por departamento (LISTA)

Ver todas las tareas

Atender tarea urgente

Atender tarea programada

Eliminar tarea de lista

Salir

yaml
Copiar código

---

## 4. Desarrollo
- Programado en **Java** con **POO**.  
- Paquetes para separar lógica (`estructuras`) e interfaz (`ui`).  
- Uso de `Stack`, `Queue`, `ArrayList`.  
- Validaciones para evitar errores.  

---

## 5. Implementación

### Entorno de ejecución
- Windows / Linux / MacOS  
- JDK 17+  

### Pasos de compilación y ejecución
```bash
# Compilar
javac main/RestauranteApp.java

# Ejecutar
java main.RestauranteApp
6. Pruebas
Casos de prueba
Caso 1: Agregar tarea urgente → aparece en la pila.

Caso 2: Atender tarea urgente → extrae la última ingresada.

Caso 3: Agregar tarea programada → aparece en la cola.

Caso 4: Atender tarea programada → extrae la primera ingresada.

Caso 5: Agregar tarea por departamento → aparece en la lista.

Caso 6: Eliminar tarea de lista → desaparece correctamente.

7. Resultados de pruebas
✔ La pila respeta LIFO
✔ La cola respeta FIFO
✔ La lista permite búsquedas y eliminaciones
✔ El menú interactivo facilita la gestión

8. Glosario de términos
Pila (Stack): estructura LIFO.

Cola (Queue): estructura FIFO.

Lista (List): colección dinámica.

POO: Programación Orientada a Objetos.

JDK: Kit de desarrollo de Java.

Consola: interfaz de texto.

9. Conclusión
André: Aplicar pilas, colas y listas me ayudó a comprender mejor estructuras de datos y POO.

Jevick: Aprendí cómo cada estructura organiza tareas en distintas situaciones del restaurante.

Jordán: Comprendí la importancia del diseño modular en Java y la relación LIFO/FIFO.

Roberto: Reforcé conceptos de estructuras de datos y validé que la planificación es clave.

10. Pasos del proyecto
Definir el alcance del proyecto.

Analizar la situación problema del restaurante.

Justificar el uso de estructuras de datos (pila, cola, lista).

Identificar requerimientos funcionales y no funcionales.

Diseñar clases principales y diagrama conceptual.

Desarrollar el código en Java usando POO.

Implementar en consola y preparar la ejecución.

Realizar pruebas con casos reales.

Documentar resultados, glosario y conclusiones.

Entregar el proyecto con README, rúbrica y repositorio en GitHub.
