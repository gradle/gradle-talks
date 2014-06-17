# Building the Builder

A Peek into Gradle's Own Build Infrastructure

## About Me

Peter Niederwieser

* Core Developer, Trainer, Consultant at Gradleware
* Linz, Austria
* Programming Languages, Chess, Chocolate, ...

## Our Infrastructure a Year Ago

* "Static" CI setup
* ~ 5 Build Machines
* Ubuntu Linux, Windows Server 2003
* Separate Machines for Perf Testing
* A Few Native Toolchains (VS/gcc/Clang)
* Manual Administration
* 24h+ Build Queues

## Where We Want(ed) To Be

* High Degree of Automation
* Scale Out Easily
* Test More Platforms & Toolchains
* High Single-Machine Performance
* Cross-Platform Testing

## Hosting Options

* Amazon EC2
* Root Servers (Hetzner)

And our winner is...

## Hosting Options

* Amazon EC2
* Root Servers (Hetzner)

And our winner is...

* Root Servers (Hetzner)

## Virtualization Options

* VMWare
* Xen
* KVM
* OpenStack

And our winner is...

## Virtualization Options

* VMWare
* Xen
* KVM
* OpenStack

And our winner is...

* KVM

## KVM

A full virtualization solution for Linux on x86 hardware.

* Kernel Based Virtual Machine
* Linux Host
* Linux, Windows, MacOS Guests

## KVM Ingredients

* VM Definition (XML)
* Disk Image
* Management Interface

## Virsh

A command line tool for managing virtual machine environments.

* `virsh list [--all]`
* `virsh start ubuntu1`
* `virsh destroy ubuntu1`
* `virsh net-list [--all]`
* `virsh net-start default`
* `virsh net-destroy default`

# Demo: KVM

## Config Mgmt Options

* Chef
* Puppet
* SaltStack
* Ansible

And our winner is...

## Config Mgmt Options

* Chef
* Puppet
* SaltStack
* Ansible

And our winner is...

* SaltStack

## SaltStack

A fast, scalable and flexible systems management software for data center automation, 
cloud orchestration, server provisioning, configuration management and more.

* Infrastructure as Data
* Windows & MacOS support
* Loosely Coupled and Flexible
* Python Based, OSS

## SaltStack (ctd.)

* Master-Slave (Minion) Topology
* Communication Bus (ZeroMQ)
* Remote Execution
* Config Management (Push and Pull)
* Cloud, Orchestration, ...

## Salt State System

* Describe Desired State in YAML Files
* Templating Language (Jinja2)

<!-- -->
    sudo: 
      group:
        - present
        - system: True
    
    {% for username, user in pillar.get('users', {}).items() %} 
    {{ username }}:
      user:
        - present
        - fullname: {{ user['fullname'] }}
        - uid: {{ user['uid'] }}
        - groups: 
          - sudo
        - shell: {{ user['shell'] }}
    {% endfor %}

## Salt Pillar

* Layer Above State System
* Describe Infrastructure With Your Own YAML Data Model
* Feeds Into Templated States

<!-- -->
    users:
      pniederw:
        fullname: Peter Niederwieser
        uid: 10001
        gid: 10001
        shell: /bin/bash
        pub_ssh_key: ssh-rsa ...
        
      fred:
        fullname: Fred Flintstone
        ...

## Salt Execution System

* `salt '*' test.ping`
* `salt-run manage.status`
* `salt ubuntu3.gradle.org cmd.run 'echo "hi from dev5!"'`
* `salt dev5.gradle.org state.sls users`
* `salt '*' state.highstate`

# Demo: Salt

## Bootstrapping Salt

* Manage Salt with Salt
* Bootstrap with Salt-SSH

## Developing Salt

* Need Local Master-Minion Setup
* Vagrant to the Rescue
* VirtualBox, VMWare for KVM

## OS Images Options

* Manually
* Ubuntu VMBuilder
* Packer

And our winner is...

## OS Images Options

* Manual
* Ubuntu VMBuilder
* Packer

And our winner is...

* Packer

## Packer

A tool for creating identical machine images for multiple platforms from a single source configuration.

* Supports Virtual Box, VMWare, KVM, EC2, etc.
* Scripted OS Installation
* Uses Original OS Installers
* Integrates with Config Mgmt Tools

## Cross-Platform Testing

<img src="img/cpptesting.png" alt="Cpp Testing" style="height: 700px; width: 700px;"/>

## Q&A

* [www.linux-kvm.org/](http://www.linux-kvm.org/)
* [www.saltstack.com/community/](http://www.saltstack.com/community/)
* [www.vagrantup.com/](http://www.vagrantup.com/)
* [www.packer.io/](http://www.packer.io/)
