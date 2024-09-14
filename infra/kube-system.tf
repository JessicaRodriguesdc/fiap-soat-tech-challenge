# resource "helm_release" "metrics_server" {
#   name       = "metrics-server"
#   repository = "https://kubernetes-sigs.github.io/metrics-server/"
#   chart      = "metrics-server"
#   version    = "3.12.1"

#   values     = [file("${path.module}/k8s/kube-system/values.yaml")]
# }