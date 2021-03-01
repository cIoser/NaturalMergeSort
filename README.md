# NaturalMergeSort

Implementation of sorting algorithm of sequential files. Records are compound of height, weight and BMI. Algorithm
sorts ascending by BMI. Files in project:

NaturalMergeSort - main, user can generate example records, launches sorting
procedures <br>
Coordinator - manages process of sorting, iterative repetitions of distribution and merging <br>
Tape - representation of file <br>
Buffer - representation of sector on disk, simulates process of reading segments bigger than one record from disk <br>
Record - representation of one record, here compound of 20 bytes
Generator - generating records from keyboard or by Random object <br>


