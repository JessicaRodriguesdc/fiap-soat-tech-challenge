resource "kubernetes_manifest" "techchallenge_namespaces" {
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/namespace.yaml"))
}

resource "kubernetes_manifest" "techchallenge_secrets" {
  for_each = fileset("${path.module}/k8s/techchallenge/secret", "*.yaml")
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/secret/${each.value}"))

  depends_on = [ kubernetes_manifest.techchallenge_namespaces ]
}

resource "kubernetes_manifest" "techchallenge_deployments" {
  for_each = fileset("${path.module}/k8s/techchallenge/deployment", "*.yaml")
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/deployment/${each.value}"))

  depends_on = [
    kubernetes_manifest.techchallenge_namespaces,
    kubernetes_manifest.techchallenge_secrets
  ]
}

resource "kubernetes_manifest" "techchallenge_services" {
  for_each = fileset("${path.module}/k8s/techchallenge/service", "*.yaml")
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/service/${each.value}"))

  depends_on = [
    kubernetes_manifest.techchallenge_namespaces,
    kubernetes_manifest.techchallenge_deployments
  ]
}

resource "kubernetes_manifest" "techchallenge_ingress" {
  for_each = fileset("${path.module}/k8s/techchallenge/ingress", "*.yaml")
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/ingress/${each.value}"))

  depends_on = [
    kubernetes_manifest.techchallenge_namespaces,
    kubernetes_manifest.techchallenge_deployments
  ]
}

resource "kubernetes_manifest" "techchallenge_hpa" {
  for_each = fileset("${path.module}/k8s/techchallenge/hpa", "*.yaml")
  manifest = yamldecode(file("${path.module}/k8s/techchallenge/hpa/${each.value}"))

  depends_on = [
    kubernetes_manifest.techchallenge_namespaces,
    kubernetes_manifest.techchallenge_services
  ]
}

