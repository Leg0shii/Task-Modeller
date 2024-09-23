# Tool Support for Task Modeling (Bachelor Thesis)

## Overview

This repository contains the source code and documentation for my bachelor thesis, titled "Tool Support for As-Is and To-Be Task Modeling". The project was developed as part of my studies at the University of Rostock and achieved the highest grade of 1.0. The thesis focuses on developing a tool that aids in the modeling and transformation of tasks, addressing both the current state (As-Is) and the desired state (To-Be) of a system.

## Motivation

During my internship at LPBK in 2021, I noticed that many applications had complex and unintuitive interfaces. Users often struggled to understand the functionalities, leading to inefficiencies and confusion. The problem was exacerbated by poor communication between developers and users. This experience highlighted the need for effective task modeling tools that can bridge the gap between technical development and user needs.

## Problem Statement

Designing complex systems requires two models: the current (As-Is) and the future (To-Be) state. However, existing tools lack robust support for transitioning between these models. This project aims to address this gap by providing a tool that facilitates both the creation and transformation of task models, ensuring that design improvements are effectively implemented.

## Objectives

The primary goal of this project is to develop a software tool that:

1. **Supports As-Is and To-Be Task Modeling**: Enables users to create and manipulate models representing both the current and future states of a system.
2. **Provides Model Transformation Capabilities**: Assists in transforming As-Is models into To-Be models, supporting a smooth design process.
3. **Incorporates Guidelines from Literature**: Implements best practices and guidelines from established methodologies like DUTCH and MUSE.

## Features

- **Concurrent Modeling**: Allows users to work on multiple models simultaneously within the same interface.
- **Task Grouping and Adaptation**: Enables grouping of tasks and adaptation of connections across model boundaries.
- **Integrated Guidelines**: Includes guidelines from the literature to assist users in creating effective models.
- **Modular Design**: Built with a modular approach, supporting extensibility and the addition of new modeling notations.

## Example Task Model
![grafik](https://github.com/user-attachments/assets/2aaab19d-ca13-40f3-bf19-68eb72aa3b7e)
This example demonstrates how different users approach the same task—in this case, solving a Wordle puzzle—and how their behaviors are modeled and integrated into a unified task model using CTT (ConcurTaskTrees) constraints. The green sections represent the individual strategies of two users: User 1 systematically follows a specific sequence for choosing letters, while User 2 adopts a more random approach, quickly clicking through options. These variations are then merged into a composite model (yellow), incorporating elements from both users' behaviors while ensuring compliance with task constraints, such as sequence and repetition. Finally, a future, optimized model (red) is presented, which refines and enforces the rules to improve task performance, demonstrating the utility of the tool in analyzing and optimizing user interactions with complex tasks.

## Implementation Details

The tool is implemented in Java using JavaFX and provides a flexible modeling environment. Key features include:

- **Multiple Editors in One Interface**: Supports multiple editors within a single interface, enabling concurrent editing of different models.
- **Support for CTT and Free Notations**: Offers two types of model notations—CTT (ConcurTaskTrees) and a free-form notation.
- **Basic Editing Functions**: Includes standard editing functions like save/load, cut/copy/paste, and centering shortcuts.
- **Visualization and Adaptation**: Provides options for visual adaptation, such as color coding and layout adjustments, to enhance model readability.

## Usage

1. **Clone the Repository**: Download the project from GitHub using `git clone`.
2. **Setup Environment**: Make sure you have Java 17 and IntelliJ or another Java-compatible IDE.
3. **Run the Application**: Open the project in your IDE and run the main class to start the tool.

## Future Work

The tool developed in this project provides a solid foundation for task modeling, but there are several areas for future enhancement:

- **Improved Flexibility**: Enhance the flexibility of the modeling environment, allowing for more dynamic interactions between model components.
- **Syntax Checking**: Implement syntax checking for various model notations to ensure consistency and correctness.
- **Simulation Capabilities**: Add functionality to simulate task models, providing insights into potential system behavior.
- **Integration of Additional Notations**: Support additional modeling notations beyond CTT, such as BPMN or UML.
- **Predefined Color Schemes**: Introduce predefined color schemes to represent specific model elements, improving visual clarity.

## Conclusion

This tool represents a step forward in supporting designers and developers in the complex task of transitioning from current systems to improved future states. By integrating best practices from task modeling literature and providing robust support for model transformation, this tool can significantly enhance the design process.

## Contact

For any questions or further information, feel free to contact me through my [GitHub profile](https://github.com/Leg0shii).

