resource "kubernetes_manifest" "nginx_ingress_namespace" {
  manifest = yamldecode(file("${path.module}/k8s/nginx-ingress/namespace.yaml"))
}

resource "helm_release" "nginx_ingress" {
  name       = "nginx-ingress"
  namespace  = "ingress-nginx"
  chart      = "ingress-nginx"
  repository = "https://kubernetes.github.io/ingress-nginx"
  version    = "4.8.1"

  values = [file("${path.module}/k8s/nginx-ingress/values.yaml")]

  set {
    name  = "controller.service.type"
    value = "LoadBalancer"
  }

  set {
    name  = "controller.publishService.enabled"
    value = "true"
  }

  depends_on = [kubernetes_manifest.nginx_ingress_namespace]
}
