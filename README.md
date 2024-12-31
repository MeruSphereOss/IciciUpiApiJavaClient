# ICICI UPI API Java Client

This project is an implementation of the **ICICI UPI API Specification** in Java. It provides a client that can communicate with ICICI’s UPI API to process transactions, encrypt/decrypt data, and generate QR codes.

## Folder Structure

### 1. model
**Path:** `src/main/java/com/merusphere/model`

This package contains Java classes responsible for preparing request data for the ICICI UPI API. The classes in this package format and organize the data before it is sent to the API.

### 2. upi
**Path:** `src/main/java/com/merusphere/upi`

This package contains Java classes responsible for interacting with the ICICI UPI API. It handles sending requests to the API and processing the responses received from the ICICI UPI endpoints.

### 3. util
**Path:** `src/main/java/com/merusphere/util`

This package includes utility classes that:
- Encrypt and decrypt the request and response data exchanged with the ICICI API.
- Generate UPI payment QR codes to facilitate transactions.

### 4. resources
**Path:** `src/main/resources`

This directory contains the essential security keys used for encryption and decryption:
- **Public Key:** ICICI's public key used for encrypting data before transmission.
- **Private Key:** The organization's private key used for decrypting responses and signing requests.

---

## Setup and Execution

Follow the steps below to build, configure, and execute the project.

### 1. **Build the Project**

Navigate to the root directory of the project and run the following Maven command to build the project:

```
mvn clean install
```

### 2. Generate the Jar file
After building the project, you can generate the executable JAR file with all dependencies included by running:

```
mvn clean compile assembly:single 
```

### 3.  Execute the JAR File
To run the generated JAR file, use the following command:

```
java -jar target/your-project-name-1.0-SNAPSHOT.jar
```

## Configuration
Specify the name of the class file to be executed in `<mainClass>class_name</mainClass>`

```
	<build>
		<plugins>
			<plugin>
				<artifactId>maven-assembly-plugin</artifactId>
				<configuration>
					<archive>
						<manifest>
							<mainClass>
								com.merusphere.upi.QrPayApi
							</mainClass>
						</manifest>
					</archive>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
			</plugin>
		</plugins>
	</build>
```