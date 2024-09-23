Spring integration adds support for enterprise integration patterns.
It enables lightweight messaging and supports integration with external systems via declarative adapters.

Spring Integration
=================

Spring Integration enables lightweight messaging within Spring-based applications and also it supports interaction
with external systems.

It provides a lot of powerful components that can greatly enhance the interconnectivity of systems and processes within
an enterprise architecture.

Example:
Consider the img-1.png. Lets say we have a source directory and destination directory.
to communicate between these two directories we need a medium.
In spring Integration, we call that medium as a pipe/channel.
Also source and destination are called as endpoints.
So our source can talk to destination over the channel.
This is what the lightweight messaging system in spring framework.

So we can send any kind of data over the channel.

Current Issue we have
===================
Consider img-2.png

If we want to send some binary data or file in my spring application(source) to a destination I need a file-adapter.
That file-adapter may need to write manually or use a 3rd part file-adapter.

Similarly, If we want to send some row objects over the channel to the database(destination), I need a database-adapter/messaging-adapter.
That database-adapter also may need to write manually or use a 3rd part file-adapter.

----------------------

What if someone say, you don't need to write adapters. just configure few things. All the transformation logics will be handled by me.
That's how spring-integration came into picture.

In Spring-Integration there are a couple of in-built adapters that can be used to send data from source to destination.

What we are going to do in this demo?
====================================
In this demo we are going to use file-adapter. Using that file adapter we will move some files from source to destination


Code explanation
================

1. We need a configuration class and inside that class we need to write a reader and writer
reader will read from source directory
writer will write to destination directory

That's why we need 2 beans.

