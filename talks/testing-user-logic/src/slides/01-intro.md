# Testing

your build logic

## Agenda

* What does it mean to test build logic?
* Types of build logic
* Testing strategies
* Designing for better testability
* Upcoming improvements

# Build testing

Why test?

## Why test?

Gradle makes building software another software endeavour.

The testing debate is over. 

Having automated tests for your code is a requirement.

Build logic is code. Your build logic needs tests.

## Build logic?

We can think about "build logic" being on a spectrum from "general" to "specific".

### General

Flexible, adaptive and usable in different contexts (e.g. plugins)

### Specific

Application of reusable logic in a specific way (e.g. builds)

