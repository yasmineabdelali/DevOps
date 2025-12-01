pipeline {
    agent any
    
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        IMAGE_NAME = "yasmineabdelali/student-management"
    }
    
    stages {
        // STAGE 1: Checkout du code
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        // STAGE 2: CRÉATION DE L'IMAGE DOCKER
        stage('Création Image Docker') {
            steps {
                script {
                    echo "=== DÉBUT DU STAGE: Création Image Docker ==="
                    echo "🔨 Construction de l'image Docker avec le Dockerfile..."
                    
                    // Build de l'image Docker
                    sh "docker build -t ${IMAGE_NAME}:latest ."
                    
                    // Tag avec le numéro de build
                    sh "docker tag ${IMAGE_NAME}:latest ${IMAGE_NAME}:\${BUILD_NUMBER}"
                    
                    echo "✅ IMAGE DOCKER CRÉÉE AVEC SUCCÈS!"
                    echo "📦 Image: ${IMAGE_NAME}:latest"
                    echo "🏷️  Tag: ${IMAGE_NAME}:\${BUILD_NUMBER}"
                }
            }
        }
        
        // STAGE 3: PUSH DE L'IMAGE VERS DOCKERHUB
        stage('Push Image DockerHub') {
            steps {
                script {
                    echo "=== DÉBUT DU STAGE: Push Image DockerHub ==="
                    echo "📤 Pushing l'image vers DockerHub..."
                    
                    // Authentification et push avec les credentials
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        sh """
                        echo "🔐 Authentification à DockerHub..."
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                        
                        echo "🚀 Push de l'image latest..."
                        docker push ${IMAGE_NAME}:latest
                        
                        echo "🚀 Push de l'image taggée..."
                        docker push ${IMAGE_NAME}:\${BUILD_NUMBER}
                        """
                    }
                    
                    echo "✅ IMAGES PUSHÉES AVEC SUCCÈS VERS DOCKERHUB!"
                    echo "🌐 Disponible sur: https://hub.docker.com/r/yasmineabdelali/student-management"
                }
            }
        }
    }
    
    post {
        always {
            echo "🧹 Nettoyage des ressources..."
            sh 'docker logout'
            sh 'docker system prune -f'
        }
        success {
            echo " "
            echo "🎉 🎉 🎉 PIPELINE EXÉCUTÉ AVEC SUCCÈS! 🎉 🎉 🎉"
            echo " "
            echo "📋 RÉSUMÉ DES 2 STAGES DEMANDÉS:"
            echo "✅ 1. STAGE 'Création Image Docker' - COMPLÉTÉ"
            echo "    → Image Docker construite à partir du Dockerfile"
            echo "    → Image taggée avec le numéro de build"
            echo " "
            echo "✅ 2. STAGE 'Push Image DockerHub' - COMPLÉTÉ"  
            echo "    → Authentification à DockerHub réussie"
            echo "    → Images poussées vers le registry DockerHub"
            echo " "
            echo "🐳 IMAGES PRODUITES:"
            echo "    - ${IMAGE_NAME}:latest"
            echo "    - ${IMAGE_NAME}:\${BUILD_NUMBER}"
            echo " "
            echo "🔗 URL: https://hub.docker.com/r/yasmineabdelali/student-management"
            echo " "
        }
        failure {
            echo "❌ Le pipeline a échoué. Consultez les logs pour le diagnostic."
        }
    }
}