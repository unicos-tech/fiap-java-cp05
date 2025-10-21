terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "7.7.0"
    }
  }

  required_version = ">= 1.13"

  backend "gcs" {
    bucket = "ribaas-terraform-state"
    prefix = "states/cp05"
  }
}
