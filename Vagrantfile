# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  # Box Ubuntu 22.04 (Bento)
  config.vm.box = "bento/ubuntu-22.04"

  # --- Réseau ---
  # Port 80 de la VM → port 8088 de ton Windows (8088 est presque toujours libre)
  config.vm.network "forwarded_port", guest: 80, host: 8088

  # IP fixe dans le réseau privé (pratique pour se connecter depuis le host)
  config.vm.network "private_network", ip: "192.168.33.10"

  # --- Dossier partagé (la ligne qui marche à tous les coups) ---
  config.vm.synced_folder ".", "/vagrant", type: "virtualbox"

  # --- VirtualBox ---
  config.vm.provider "virtualbox" do |vb|
    vb.name = "student-management-ubuntu"   # nom clair dans VirtualBox
    vb.memory = "5000"                       # 5 Go de RAM
    vb.cpus = 2                              # 2 cœurs
    # vb.gui = true                          # décommente si tu veux voir l’interface graphique
  end

  # --- Provisioning (installations de base) ---
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get upgrade -y
    
    # Outils de base + Java 11 + Maven (parfait pour ton projet Spring Boot)
    sudo apt-get install -y git curl vim wget unzip
    sudo apt-get install -y openjdk-11-jdk maven
    
    # Optionnel : Node.js + npm si tu en as besoin plus tard
    # curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
    # sudo apt-get install -y nodejs
  SHELL

end