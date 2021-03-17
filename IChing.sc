IChing {
	var <>hexagram;

	*new {
		^super.new.consult;
	}

	// consult the oracle - get an hexagram
	consult {
		var oldyin, yang, yin, oldyang;

		oldyin = Dictionary.newFrom( List[
			\num, 6,
			\yarrow, 1/16,
			\coin, 2/16,
			\name, "old yin",
			\signification, "yin changing into yang",
			\symbol, "---x---",
			\binary, 0,
			\changing, true,
		]);
		yang = Dictionary.newFrom( List[
			\num, 7,
			\yarrow, 5/16,
			\coin, 6/16,
			\name, "yang",
			\signification, "yang unchanging",
			\symbol, "-------",
			\binary, 1,
			\changing, false,
		]);
		yin = Dictionary.newFrom( List[
			\num, 8,
			\yarrow, 7/16,
			\coin, 6/16,
			\name, "yin",
			\signification, "yin unchanging",
			\symbol, "---  ---",
			\binary, 0,
			\changing, false,
		]);
		oldyang = Dictionary.newFrom( List[
			\num, 9,
			\yarrow, 3/16,
			\coin, 2/16,
			\name, "old yang",
			\signification, "yang changing into yin",
			\symbol, "---o---",
			\binary, 1,
			\changing, true,
		]);

		hexagram = Array.fill( 6, { |i|
			[oldyin, yang, yin, oldyang].wchoose([oldyin[\yarrow], yang[\yarrow], yin[\yarrow], oldyang[\yarrow]]);
			// var val = [oldyin, yang, yin, oldyang].wchoose([oldyin[\yarrow], yang[\yarrow], yin[\yarrow], oldyang[\yarrow]]);
			// [i, val[\symbol]].postln;
			// val;
		});

		IChing.post(hexagram);
	}

	// return the hexagram as a decimal number
	// WARNING: It does not correspond to wilhelm's numbering, it's a translation to binary form
	*hexagramNumber { |hex|
		var num = IChing.hexagramAsBinary(hex).reverse * 2.pow((0..5));
		^num.sum.asInt;
	}

	// return the hexagram as a Binary array
	*hexagramAsBinary{ |hex|
		var binary = Array.fill(hex.size, {|i|
			hex[i][\binary]
		});
		^binary.reverse;
	}

	// print hexagram
	*post { |hex=#[0,0,0,0,0,0]|
		postf("hexagram number: %\n", IChing.hexagramNumber(hex));
		postf("pos \thexagram \tnum \tbin \tsignification\n");
		hex.reverse.do{ |item, i|
			postf(" %\t\t% \t\t% \t\t\t% \t\t%\n",hex.size-i, item[\symbol], item[\num], item[\binary],  item[\signification]);
		};
		// hexagram.do{|item,i| [i, item[\symbol], item[\binary]].postln};
	}

	post {
		IChing.post(this.hexagram);
	}

	// return line number [6..9] given it's position [1..6]
	lineNum { |lineposition|
		^hexagram[lineposition-1][\num];
	}

	// return binary value of line
	lineBinary { |lineposition|
		^hexagram[lineposition-1][\binary];
	}

	// return true if line is changing
	lineChanging { |lineposition|
		^hexagram[lineposition-1][\changing];
	}

	// return the hexagram number
	hexagramNumber { |lineposition|
		^IChing.hexagramNumber(hexagram);
	}

	// return the hexagram as a binary array
	asBinary {
		^IChing.hexagramAsBinary(hexagram);
	}
	// return the hexagram as a binary array
	asBinaryArray {
		^IChing.hexagramAsBinary(hexagram);
	}

}