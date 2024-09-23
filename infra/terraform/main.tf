terraform {
  required_providers {
    kubernetes = {
      source = "hashicorp/kubernetes"
      version = "2.17.0"
    }
  }
}

provider "kubernetes" {
  config_path    = "~/.kube/config"
  config_context = var.config_context
}

provider "helm" {
  kubernetes {
    config_path    = "~/.kube/config"
    config_context = var.config_context
  }
}
