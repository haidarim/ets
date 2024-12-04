## DevOps: 

**DevOps vs System Administration:**

### Provision:
Provisioning focuses on setting up and configuring the foundational components of IT infrastructure. It involves the initial creation, allocation, and configuration of resources such as servers, networks, storage, and other hardware or virtual components. For example:  deploying virtual machines, creating databases, setting up networking configurations, etc.

Used tool commonly for this purpose is `terraform` which is provided by HashiCorp.
Terraform is primarily used for infrastructure provisioning and orchestration.
It allows to define and manage the infrastructure as a code (IaC). The configuration and setup (infrastructure definition) will be described a HashiCorp Configuration Language (HCL) or JSON. 


### Automation:
Automation deals with repetitive tasks and operational processes related to managing and maintaining IT infrastructure.  It aims to streamline and standardize workflows, reducing manual effort and human error in routine tasks. Automation improves efficiency, consistency, and reliability by scripting and orchestrating tasks across multiple systems or environments.

Using tools like Ansible to automate software deployment, configuration management, patching, monitoring setup, etc. Ansible uses SSH (or other protocols) to communicate with servers and execute predefined tasks (playbooks) that configure and manage systems. Playbooks are written in YAML and can define tasks such as installing software, configuring services, deploying applications, and more.


### Monitoring: 
 Monitoring is concerned with observing and gathering data about the performance, health, and status of IT infrastructure and applications.  It provides insights into system behavior, identifies issues or anomalies, and helps ensure optimal performance and availability. Monitoring tools like Grafana helps to to track metrics such as CPU usage, memory usage, response times, etc.
 
Grafana is a powerful open-source platform used for monitoring and observability, widely known for its visualization capabilities. Features of Grafana: 
- Visualization and Dashboards
- Graphing
- Alerting
- Annotation 


### Container vs VM:

### Kubernetes (K8s): K(8 chars) s


### Used tools and techniques in this project: 

### CI CD:

Commonly used structure for pipeline stages in CI/CD in order: 0-100
```yml
#------- checkout and setup 0-10
# Clone the repository and set up the environment.
# Initialize any necessary tools or configurations for the CI/CD process.

#------- build 10-30
# Compile source code, bundle assets, or prepare artifacts needed for testing and 
# deployment.
# If using Docker, build Docker images that encapsulate the application and its  
# dependencies.

#------- Test  30-50
# Run automated tests (unit tests, integration tests, etc.) to validate the changes.
# Tests may include checks specific to containerized environments, such as container # health checks or compatibility tests.

#------- Deploy of Staging/Testing Environment 50-70
# Deploy the application to a staging or testing environment.
# If using Kubernetes, this stage includes deploying Docker images to Kubernetes 
# clusters.
# Kubernetes settings (such as deployment configurations, service definitions, and 
# ingress rules) are applied to orchestrate containerized applications.

#------- Approval and Manual Checks 70-90
# Conduct manual approval steps or additional checks before proceeding to production # deployment.
# Verify Kubernetes configurations and ensure they align with production 
# requirements (e.g., scaling settings, resource limits).

#------- Deploy to Production 90-100
# Deploy the application to production environments hosted on Kubernetes.
# Apply Kubernetes manifests (YAML files) that define the desired state of 
# applications, services, deployments, and other resources.
# Post-deployment tasks may include updating Kubernetes secrets/configmaps, rolling # updates, or executing database migrations.
```






