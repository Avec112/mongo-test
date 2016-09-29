node {
    def mvnHome
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/Avec112/mongo-test.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('Build') {
        // Run the maven build
        sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        //sh "'${mvnHome}/bin/mvn' clean package"
    }
    stage('QA') {
        parallel(
            "build-verification": {
                junit: '**/target/surefire-reports/TEST-*.xml'
                // jacoco?
                archive: '**/target/*.jar'
            },
            "sonarqube": {
                sh "'${mvnHome}/bin/mvn' sonar:sonar"
                // TODO how to notify jenkins based on sonar error level...
            }
        )
    }
    stage('Test') {
        parallel(
            "integration": {
                // start docker mongodb
            },
            "performance": {
                // run some performance testing
            }
        )
    }
    stage('Deploy') {
        parallel(
            "integration": {

            },
            "performance": {

            }
        )
    }

    //stage('Results') {
    //    junit: '**/target/surefire-reports/TEST-*.xml'
    //    archive: '**/target/*.jar'
    //}
}