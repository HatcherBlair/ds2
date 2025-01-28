#!/usr/bin/perl
use strict;

if (! @ARGV) {
  die "no argument: $!";
}

# Conventions concerning filenames:
#    "D" = directed
#  "adj" = adjacency list (unweighted)
#
# (Weighted graphs are not stored as adjacency lists)

foreach my $inpfile (@ARGV) {
  my $directed = ($inpfile =~ m/D/) ? "->" : "--";
  my $directed = ($inpfile =~ m/D/) ? "->" : "--";
  (my $outfile = $inpfile) =~ s/.txt/.gv/;
  $outfile =~ s/.*\///;
  (my $pngfile = $outfile) =~ s/.gv/.png/;
  
  open my $INP, '<', $inpfile or die $!;
  open my $OUT, '>', $outfile or die $!;
  my $skip1 = <$INP>; my $skip2 = <$INP>; # skip two lines

  if ($directed eq "->") {
    print $OUT "digraph G {\n";
  } else {
    print $OUT "graph G {\n";
  }
  while (<$INP>) {
    my @val = split;
    if (@val == 2) {      
      printf $OUT "  %d %s %d;\n", $val[0], $directed, $val[1];
    } elsif (@val == 3) {      
      printf $OUT "  %d %s %d [len=%s, label=%s];\n", $val[0], $directed, $val[1], $val[2], $val[2];
    }
  }
  print $OUT "}\n";
  close $OUT;
  system "dot -Tpng -o $pngfile $outfile"
}
