# Use OpenJDK 21 as the base image
FROM openjdk:21

# Set working directory inside the container
WORKDIR /app

# Copy the compiled Java class to the container
COPY Server.class /app/

# Expose port 5000
EXPOSE 5000

# Run the Java application
CMD ["java", "Server"]