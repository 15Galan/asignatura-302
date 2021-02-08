; --- Copyright Ricardo Conejo 2008. All rights reserved. -----------------
; File:      Funcion.j
; Author:    Jonathan Meyer, 9 June 2008
; Purpose:   Prints out a function value written in Java Assembler
; -------------------------------------------------------------------------

.class public JPL
.super java/lang/Object

.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
   .limit stack 2
   .limit locals 2
   
   aload_0
   iconst_0
   aaload
   invokestatic java/lang/Integer/parseInt(Ljava/lang/String;)I
   istore_1
   getstatic java/lang/System/out Ljava/io/PrintStream;
   iload_1
   invokestatic  JPL/main(I)I
   invokevirtual java/io/PrintStream/println(I)V

   return
.end method

