provider "google" {
  project = "cp5java"
  region  = "southamerica-east1"
}

locals {
  services = {
    "ms-checkout" = "southamerica-east1-docker.pkg.dev/cp5java/cp5repo/ms-checkout:latest"
    # "ms-pagamento"     = "southamerica-east1-docker.pkg.dev/cp5java/cp5repo/ms-pagamento:latest"
    # "ms-sms"   = "southamerica-east1-docker.pkg.dev/cp5java/cp5repo/ms-sms:latest"
    "ms-stock"   = "southamerica-east1-docker.pkg.dev/cp5java/cp5repo/ms-stock:latest"
  }
}

resource "google_cloud_run_v2_service" "service" {
  for_each            = local.services
  name                = each.key
  location            = "southamerica-east1"
  deletion_protection = false
  ingress             = "INGRESS_TRAFFIC_ALL"

  template {
    containers {
      image = each.value
      ports { container_port = 8080 }
      resources {
        cpu_idle = true
        limits = {
          cpu    = "1"
          memory = "512Mi"
        }
      }

      env {
        name = "RABBITMQ_HOST"
        value_source {
          secret_key_ref {
            secret  = "projects/319629332081/secrets/rabbitmq-host"
            version = "latest"
          }
        }
      }

      env {
        name  = "RABBITMQ_PORT"
        value = "5672"
      }
      env {
        name = "RABBITMQ_USER"
        value_source {
          secret_key_ref {
            secret  = "projects/319629332081/secrets/rabbitmq-user"
            version = "latest"
          }
        }
      }

      env {
        name = "RABBITMQ_PASSWORD"
        value_source {
          secret_key_ref {
            secret  = "projects/319629332081/secrets/rabbitmq-password"
            version = "latest"
          }
        }
      }

      env {
        name = "MONGODB_URI"
        value_source {
          secret_key_ref {
            secret  = "projects/319629332081/secrets/mongodb-connection-string"
            version = "latest"
          }
        }
      }
    }

    annotations = {
      "redeploy-trigger" = timestamp()
    }
  }

  scaling {
    min_instance_count = 1
    max_instance_count = 1
  }
}

resource "google_cloud_run_v2_service_iam_member" "noauth" {
  for_each = local.services
  location = google_cloud_run_v2_service.service[each.key].location
  name     = google_cloud_run_v2_service.service[each.key].name
  role     = "roles/run.invoker"
  member   = "allUsers"
}
