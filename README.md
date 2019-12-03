# accordant-usecases
This repository contains uses cases' projects using ACCORDANT framework. These projects requires the [ACCORDANT's eclipse plugins](https://github.com/kmilo-castellanos/accordant). For more information, please visit the [ACCORDANT Site](https://sistemasproyectos.uniandes.edu.co/iniciativas/architlab/research/big-data-analytics/).

* busdelay: contais the use case 1 (UC1) which predicts the transportation delays using regression tree model in stream processing.
* avionics: comprises avionics use cases 2 and 3. UC2 is a risk clustering of NMAC events in batch processing, and UC3 is a NMAC event detection based on decision tree model in micro-batch processing.
* enso: contains El Nino/Southern Oscillation use case (UC4)  based on a polynomial regression model and batch processing.

Each project has three folders:
* src: contains the use case models described using the ACCORDANT specific languages. Architectural inputs model (.ail), functional model (.afl) and the deployment model (.adl).
* pmml-models: contains the analytics model for each use case in PMML format
* src-gen: contains the generated code corresponding to functional, and Infrastructure as Code (kubenetes-gen folder)
