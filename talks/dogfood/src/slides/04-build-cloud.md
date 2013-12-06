# Gradleware Build Cloud

## Where we are

* "Static" TeamCity setup
* ~ 5 build machines
* Windows 7 and Ubuntu Linux
* Separate machines for performance tests
* A few Visual Studio/gcc/Clang toolchains
* Manual configuration
* 24+ hour build queues

## Where we want to be

* As many machines as we need, easy to add more
* Windows XP/7/8/Server
* Different Linux distributions
* Mac OS
* Many different Visual Studio/gcc/Clang/etc. toolchains
* Automatic configuration
* Cross-platform testing

## Hosting Options

* Amazon EC2
* Data Center (Hetzner)

And the winner is...

## Hosting Options

* Amazon EC2
* Data Center (Hetzner)

And the winner is...

* Data Center (Hetzner)

## Virtualization Options

* VMWare
* Xen
* KVM
* OpenStack

And the winner is...

## Virtualization Options

* VMWare
* Xen
* KVM
* OpenStack

And the winner is...

* KVM

## Configuration Management Tools

* Chef
* Puppet
* SaltStack
* Ansible

And the winner is...

## Configuration Mgmt Tools

* Chef
* Puppet
* SaltStack
* Ansible

And the winner is...

* SaltStack

## SaltStack

* Distributed Execution and CM platform
* Infrastructure as code
* Windows & MacOS support
* Push and pull
* Loosely coupled and flexible

## Modeling with Salt

einstein.sls

    hostname: einstein
    fqdn: einstein.gradle.org
    ip: 121.46.164.123
    roles: 
      - build-vm-linux
  
    tcagents:
      tcagent1:
        id: einstein-agent1
        port: 20331
      tcagent2:
        id: einstein-agent2
        port: 20332

## Getting Einstein in Shape

tc_agents.sls

    {% for agent in pillar['tc_agents'].itervalues() %} 
    {{ agent['name'] }}-service:
      file:
        - managed
        - name: /etc/init/{{ agent['name'] }}.conf
        - source: salt://build-vm/agents/agent-service.conf
        - template: jinja
        - context:
            agent: {{ agent }}
      service:
        - running
        - name: {{ agent['name'] }} 
        - watch:
            - file: {{ agent['name'] }}-service
    {% endfor %}

## Cross-Platform Testing

<img src="img/cpptesting.png" alt="Cpp Testing" style="height: 700px; width: 700px;"/>







