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
   //stage('Test') {
     // sh "'${mvnHome}/bin/mvn' sonar:sonar"

   //}
   stage('Results') {
       junit: '**/target/surefire-reports/TEST-*.xml'
       archive: '**/target/*.jar'
       //step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
       //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
       /* parallel (
           "text1":{
           },
           "text2":{
           }
        )
        */
   }
}