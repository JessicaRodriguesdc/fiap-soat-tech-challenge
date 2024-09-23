resource "kubernetes_manifest" "monitoring_namespaces" {
  manifest = yamldecode(file("${path.module}/../k8s/monitoring/namespace.yaml"))
}

resource "helm_release" "kube-prometheus-stack" {
  name       = "kube-prometheus-stack"
  namespace  = "monitoring"
  chart      = "kube-prometheus-stack"
  repository = "https://prometheus-community.github.io/helm-charts"
  version    = "51.2.0"
  values     = [file("${path.module}/../k8s/monitoring/kube-prometheus-stack/values.yaml")]

  timeout = 600

  depends_on = [
    kubernetes_manifest.monitoring_namespaces,
    helm_release.metrics_server
  ]
}

resource "kubernetes_manifest" "grafana_configmaps" {
  manifest = yamldecode(file("${path.module}/../k8s/monitoring/grafana/configmap.yaml"))

  depends_on = [
    kubernetes_manifest.monitoring_namespaces
  ]
}

resource "helm_release" "grafana" {
  name       = "grafana"
  namespace  = "monitoring"
  chart      = "grafana"
  repository = "https://grafana.github.io/helm-charts"
  version    = "8.5.1"
  values     = [file("${path.module}/../k8s/monitoring/grafana/values.yaml")]

  timeout = 600

  depends_on = [
    kubernetes_manifest.monitoring_namespaces,
    kubernetes_manifest.grafana_configmaps,
    helm_release.metrics_server
  ]
}
