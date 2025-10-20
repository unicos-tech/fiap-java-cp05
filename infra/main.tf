provider "google" {
  project = "cp5java"
  region  = "southamerica-east1"
}

resource "google_cloud_run_v2_service" "ms-checkout" {
  name                = "ms-checkout"
  location            = "southamerica-east1"
  deletion_protection = false
  ingress             = "INGRESS_TRAFFIC_ALL"

  template {
    containers {
      image = "IMAGE_URL_PLACEHOLDER"
      ports { container_port = 8080 }
      resources {
        # cpu_idle = false
        limits = {
          cpu    = "1"
          memory = "512Mi"
        }
      }
    }
  }

  scaling {
    min_instance_count = 1
    max_instance_count = 1
  }
}

resource "google_cloud_run_v2_service_iam_member" "noauth" {
  location = google_cloud_run_v2_service.ms-checkout.location
  name     = google_cloud_run_v2_service.ms-checkout.name
  role     = "roles/run.invoker"
  member   = "allUsers"
}
