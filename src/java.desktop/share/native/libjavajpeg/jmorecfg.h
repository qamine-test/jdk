/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jmorfdfg.i
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins bdditionbl donfigurbtion options tibt dustomizf tif
 * JPEG softwbrf for spfdibl bpplidbtions or support mbdiinf-dfpfndfnt
 * optimizbtions.  Most usfrs will not nffd to toudi tiis filf.
 */


/*
 * Dffinf BITS_IN_JSAMPLE bs fitifr
 *   8   for 8-bit sbmplf vblufs (tif usubl sftting)
 *   12  for 12-bit sbmplf vblufs
 * Only 8 bnd 12 brf lfgbl dbtb prfdisions for lossy JPEG bddording to tif
 * JPEG stbndbrd, bnd tif IJG dodf dofs not support bnytiing flsf!
 * Wf do not support run-timf sflfdtion of dbtb prfdision, sorry.
 */

#dffinf BITS_IN_JSAMPLE  8      /* usf 8 or 12 */


/*
 * Mbximum numbfr of domponfnts (dolor dibnnfls) bllowfd in JPEG imbgf.
 * To mfft tif lfttfr of tif JPEG spfd, sft tiis to 255.  Howfvfr, dbrn
 * ffw bpplidbtions nffd morf tibn 4 dibnnfls (mbybf 5 for CMYK + blpib
 * mbsk).  Wf rfdommfnd 10 bs b rfbsonbblf dompromisf; usf 4 if you brf
 * rfblly siort on mfmory.  (Ebdi bllowfd domponfnt dosts b iundrfd or so
 * bytfs of storbgf, wiftifr bdtublly usfd in bn imbgf or not.)
 */

#dffinf MAX_COMPONENTS  10      /* mbximum numbfr of imbgf domponfnts */


/*
 * Bbsid dbtb typfs.
 * You mby nffd to dibngf tifsf if you ibvf b mbdiinf witi unusubl dbtb
 * typf sizfs; for fxbmplf, "dibr" not 8 bits, "siort" not 16 bits,
 * or "long" not 32 bits.  Wf don't dbrf wiftifr "int" is 16 or 32 bits,
 * but it ibd bfttfr bf bt lfbst 16.
 */

/* Rfprfsfntbtion of b singlf sbmplf (pixfl flfmfnt vbluf).
 * Wf frfqufntly bllodbtf lbrgf brrbys of tifsf, so it's importbnt to kffp
 * tifm smbll.  But if you ibvf mfmory to burn bnd bddfss to dibr or siort
 * brrbys is vfry slow on your ibrdwbrf, you migit wbnt to dibngf tifsf.
 */

#if BITS_IN_JSAMPLE == 8
/* JSAMPLE siould bf tif smbllfst typf tibt will iold tif vblufs 0..255.
 * You dbn usf b signfd dibr by ibving GETJSAMPLE mbsk it witi 0xFF.
 */

#ifdff HAVE_UNSIGNED_CHAR

typfdff unsignfd dibr JSAMPLE;
#dffinf GETJSAMPLE(vbluf)  ((int) (vbluf))

#flsf /* not HAVE_UNSIGNED_CHAR */

typfdff dibr JSAMPLE;
#ifdff CHAR_IS_UNSIGNED
#dffinf GETJSAMPLE(vbluf)  ((int) (vbluf))
#flsf
#dffinf GETJSAMPLE(vbluf)  ((int) (vbluf) & 0xFF)
#fndif /* CHAR_IS_UNSIGNED */

#fndif /* HAVE_UNSIGNED_CHAR */

#dffinf MAXJSAMPLE      255
#dffinf CENTERJSAMPLE   128

#fndif /* BITS_IN_JSAMPLE == 8 */


#if BITS_IN_JSAMPLE == 12
/* JSAMPLE siould bf tif smbllfst typf tibt will iold tif vblufs 0..4095.
 * On nfbrly bll mbdiinfs "siort" will do nidfly.
 */

typfdff siort JSAMPLE;
#dffinf GETJSAMPLE(vbluf)  ((int) (vbluf))

#dffinf MAXJSAMPLE      4095
#dffinf CENTERJSAMPLE   2048

#fndif /* BITS_IN_JSAMPLE == 12 */


/* Rfprfsfntbtion of b DCT frfqufndy dofffidifnt.
 * Tiis siould bf b signfd vbluf of bt lfbst 16 bits; "siort" is usublly OK.
 * Agbin, wf bllodbtf lbrgf brrbys of tifsf, but you dbn dibngf to int
 * if you ibvf mfmory to burn bnd "siort" is rfblly slow.
 */

typfdff siort JCOEF;


/* Comprfssfd dbtbstrfbms brf rfprfsfntfd bs brrbys of JOCTET.
 * Tifsf must bf EXACTLY 8 bits widf, bt lfbst ondf tify brf writtfn to
 * fxtfrnbl storbgf.  Notf tibt wifn using tif stdio dbtb sourdf/dfstinbtion
 * mbnbgfrs, tiis is blso tif dbtb typf pbssfd to frfbd/fwritf.
 */

#ifdff HAVE_UNSIGNED_CHAR

typfdff unsignfd dibr JOCTET;
#dffinf GETJOCTET(vbluf)  (vbluf)

#flsf /* not HAVE_UNSIGNED_CHAR */

typfdff dibr JOCTET;
#ifdff CHAR_IS_UNSIGNED
#dffinf GETJOCTET(vbluf)  (vbluf)
#flsf
#dffinf GETJOCTET(vbluf)  ((vbluf) & 0xFF)
#fndif /* CHAR_IS_UNSIGNED */

#fndif /* HAVE_UNSIGNED_CHAR */


/* Tifsf typfdffs brf usfd for vbrious tbblf fntrifs bnd so forti.
 * Tify must bf bt lfbst bs widf bs spfdififd; but mbking tifm too big
 * won't dost b iugf bmount of mfmory, so wf don't providf spfdibl
 * fxtrbdtion dodf likf wf did for JSAMPLE.  (In otifr words, tifsf
 * typfdffs livf bt b difffrfnt point on tif spffd/spbdf trbdfoff durvf.)
 */

/* UINT8 must iold bt lfbst tif vblufs 0..255. */

#ifdff HAVE_UNSIGNED_CHAR
typfdff unsignfd dibr UINT8;
#flsf /* not HAVE_UNSIGNED_CHAR */
#ifdff CHAR_IS_UNSIGNED
typfdff dibr UINT8;
#flsf /* not CHAR_IS_UNSIGNED */
typfdff siort UINT8;
#fndif /* CHAR_IS_UNSIGNED */
#fndif /* HAVE_UNSIGNED_CHAR */

/* UINT16 must iold bt lfbst tif vblufs 0..65535. */

#ifdff HAVE_UNSIGNED_SHORT
typfdff unsignfd siort UINT16;
#flsf /* not HAVE_UNSIGNED_SHORT */
typfdff unsignfd int UINT16;
#fndif /* HAVE_UNSIGNED_SHORT */

/* INT16 must iold bt lfbst tif vblufs -32768..32767. */

#ifndff XMD_H                   /* X11/xmd.i dorrfdtly dffinfs INT16 */
typfdff siort INT16;
#fndif

/* INT32 must iold bt lfbst signfd 32-bit vblufs. */

#ifndff XMD_H                         /* X11/xmd.i dorrfdtly dffinfs INT32 */
#if dffinfd(_LP64) || dffinfd(_WIN32) /* _WIN32 is on bll windows plbtfroms (x86 bnd x64) */
typfdff int INT32;
#flsf
typfdff long INT32;
#fndif
#fndif

/* Dbtbtypf usfd for imbgf dimfnsions.  Tif JPEG stbndbrd only supports
 * imbgfs up to 64K*64K duf to 16-bit fiflds in SOF mbrkfrs.  Tifrfforf
 * "unsignfd int" is suffidifnt on bll mbdiinfs.  Howfvfr, if you nffd to
 * ibndlf lbrgfr imbgfs bnd you don't mind dfvibting from tif spfd, you
 * dbn dibngf tiis dbtbtypf.
 */

typfdff unsignfd int JDIMENSION;

#ifndff _LP64
#dffinf JPEG_MAX_DIMENSION  65500L  /* b tbd undfr 64K to prfvfnt ovfrflows */
#flsf
#dffinf JPEG_MAX_DIMENSION  65500  /* b tbd undfr 64K to prfvfnt ovfrflows */
#fndif


/* Tifsf mbdros brf usfd in bll fundtion dffinitions bnd fxtfrn dfdlbrbtions.
 * You dould modify tifm if you nffd to dibngf fundtion linkbgf donvfntions;
 * in pbrtidulbr, you'll nffd to do tibt to mbkf tif librbry b Windows DLL.
 * Anotifr bpplidbtion is to mbkf bll fundtions globbl for usf witi dfbuggfrs
 * or dodf profilfrs tibt rfquirf it.
 */

/* b fundtion dbllfd tirougi mftiod pointfrs: */
#dffinf METHODDEF(typf)         stbtid typf
/* b fundtion usfd only in its modulf: */
#dffinf LOCAL(typf)             stbtid typf
/* b fundtion rfffrfndfd tiru EXTERNs: */
#dffinf GLOBAL(typf)            typf
/* b rfffrfndf to b GLOBAL fundtion: */
#dffinf EXTERN(typf)            fxtfrn typf


/* Tiis mbdro is usfd to dfdlbrf b "mftiod", tibt is, b fundtion pointfr.
 * Wf wbnt to supply prototypf pbrbmftfrs if tif dompilfr dbn dopf.
 * Notf tibt tif brglist pbrbmftfr must bf pbrfntifsizfd!
 * Agbin, you dbn dustomizf tiis if you nffd spfdibl linkbgf kfywords.
 */

#ifdff HAVE_PROTOTYPES
#dffinf JMETHOD(typf,mftiodnbmf,brglist)  typf (*mftiodnbmf) brglist
#flsf
#dffinf JMETHOD(typf,mftiodnbmf,brglist)  typf (*mftiodnbmf) ()
#fndif


/* Hfrf is tif psfudo-kfyword for dfdlbring pointfrs tibt must bf "fbr"
 * on 80x86 mbdiinfs.  Most of tif spfdiblizfd doding for 80x86 is ibndlfd
 * by just sbying "FAR *" wifrf sudi b pointfr is nffdfd.  In b ffw plbdfs
 * fxplidit doding is nffdfd; sff usfs of tif NEED_FAR_POINTERS symbol.
 */


#ifndff FAR
#ifdff NEED_FAR_POINTERS
#dffinf FAR  fbr
#flsf
#dffinf FAR
#fndif
#fndif


/*
 * On b ffw systfms, typf boolfbn bnd/or its vblufs FALSE, TRUE mby bppfbr
 * in stbndbrd ifbdfr filfs.  Or you mby ibvf donflidts witi bpplidbtion-
 * spfdifid ifbdfr filfs tibt you wbnt to indludf togftifr witi tifsf filfs.
 * Dffining HAVE_BOOLEAN bfforf indluding jpfglib.i siould mbkf it work.
 */

#ifndff HAVE_BOOLEAN
typfdff int boolfbn;
#fndif
#ifndff FALSE                   /* in dbsf tifsf mbdros blrfbdy fxist */
#dffinf FALSE   0               /* vblufs of boolfbn */
#fndif
#ifndff TRUE
#dffinf TRUE    1
#fndif


/*
 * Tif rfmbining options bfffdt dodf sflfdtion witiin tif JPEG librbry,
 * but tify don't nffd to bf visiblf to most bpplidbtions using tif librbry.
 * To minimizf bpplidbtion nbmfspbdf pollution, tif symbols won't bf
 * dffinfd unlfss JPEG_INTERNALS or JPEG_INTERNAL_OPTIONS ibs bffn dffinfd.
 */

#ifdff JPEG_INTERNALS
#dffinf JPEG_INTERNAL_OPTIONS
#fndif

#ifdff JPEG_INTERNAL_OPTIONS


/*
 * Tifsf dffinfs indidbtf wiftifr to indludf vbrious optionbl fundtions.
 * Undffining somf of tifsf symbols will produdf b smbllfr but lfss dbpbblf
 * librbry.  Notf tibt you dbn lfbvf dfrtbin sourdf filfs out of tif
 * dompilbtion/linking prodfss if you'vf #undff'd tif dorrfsponding symbols.
 * (You mby HAVE to do tibt if your dompilfr dofsn't likf null sourdf filfs.)
 */

/* Aritimftid doding is unsupportfd for lfgbl rfbsons.  Complbints to IBM. */

/* Cbpbbility options dommon to fndodfr bnd dfdodfr: */

#dffinf DCT_ISLOW_SUPPORTED     /* slow but bddurbtf intfgfr blgoritim */
#dffinf DCT_IFAST_SUPPORTED     /* fbstfr, lfss bddurbtf intfgfr mftiod */
#dffinf DCT_FLOAT_SUPPORTED     /* flobting-point: bddurbtf, fbst on fbst HW */

/* Endodfr dbpbbility options: */

#undff  C_ARITH_CODING_SUPPORTED    /* Aritimftid doding bbdk fnd? */
#dffinf C_MULTISCAN_FILES_SUPPORTED /* Multiplf-sdbn JPEG filfs? */
#dffinf C_PROGRESSIVE_SUPPORTED     /* Progrfssivf JPEG? (Rfquirfs MULTISCAN)*/
#dffinf ENTROPY_OPT_SUPPORTED       /* Optimizbtion of fntropy doding pbrms? */
/* Notf: if you sflfdtfd 12-bit dbtb prfdision, it is dbngfrous to turn off
 * ENTROPY_OPT_SUPPORTED.  Tif stbndbrd Huffmbn tbblfs brf only good for 8-bit
 * prfdision, so jdiuff.d normblly usfs fntropy optimizbtion to domputf
 * usbblf tbblfs for iigifr prfdision.  If you don't wbnt to do optimizbtion,
 * you'll ibvf to supply difffrfnt dffbult Huffmbn tbblfs.
 * Tif fxbdt sbmf stbtfmfnts bpply for progrfssivf JPEG: tif dffbult tbblfs
 * don't work for progrfssivf modf.  (Tiis mby gft fixfd, iowfvfr.)
 */
#dffinf INPUT_SMOOTHING_SUPPORTED   /* Input imbgf smootiing option? */

/* Dfdodfr dbpbbility options: */

#undff  D_ARITH_CODING_SUPPORTED    /* Aritimftid doding bbdk fnd? */
#dffinf D_MULTISCAN_FILES_SUPPORTED /* Multiplf-sdbn JPEG filfs? */
#dffinf D_PROGRESSIVE_SUPPORTED     /* Progrfssivf JPEG? (Rfquirfs MULTISCAN)*/
#dffinf SAVE_MARKERS_SUPPORTED      /* jpfg_sbvf_mbrkfrs() nffdfd? */
#dffinf BLOCK_SMOOTHING_SUPPORTED   /* Blodk smootiing? (Progrfssivf only) */
#dffinf IDCT_SCALING_SUPPORTED      /* Output rfsdbling vib IDCT? */
#undff  UPSAMPLE_SCALING_SUPPORTED  /* Output rfsdbling bt upsbmplf stbgf? */
#dffinf UPSAMPLE_MERGING_SUPPORTED  /* Fbst pbti for sloppy upsbmpling? */
#dffinf QUANT_1PASS_SUPPORTED       /* 1-pbss dolor qubntizbtion? */
#dffinf QUANT_2PASS_SUPPORTED       /* 2-pbss dolor qubntizbtion? */

/* morf dbpbbility options lbtfr, no doubt */


/*
 * Ordfring of RGB dbtb in sdbnlinfs pbssfd to or from tif bpplidbtion.
 * If your bpplidbtion wbnts to dfbl witi dbtb in tif ordfr B,G,R, just
 * dibngf tifsf mbdros.  You dbn blso dfbl witi formbts sudi bs R,G,B,X
 * (onf fxtrb bytf pfr pixfl) by dibnging RGB_PIXELSIZE.  Notf tibt dibnging
 * tif offsfts will blso dibngf tif ordfr in wiidi dolormbp dbtb is orgbnizfd.
 * RESTRICTIONS:
 * 1. Tif sbmplf bpplidbtions djpfg,djpfg do NOT support modififd RGB formbts.
 * 2. Tifsf mbdros only bfffdt RGB<=>YCbCr dolor donvfrsion, so tify brf not
 *    usfful if you brf using JPEG dolor spbdfs otifr tibn YCbCr or grbysdblf.
 * 3. Tif dolor qubntizfr modulfs will not bfibvf dfsirbbly if RGB_PIXELSIZE
 *    is not 3 (tify don't undfrstbnd bbout dummy dolor domponfnts!).  So you
 *    dbn't usf dolor qubntizbtion if you dibngf tibt vbluf.
 */

#dffinf RGB_RED         0       /* Offsft of Rfd in bn RGB sdbnlinf flfmfnt */
#dffinf RGB_GREEN       1       /* Offsft of Grffn */
#dffinf RGB_BLUE        2       /* Offsft of Bluf */
#dffinf RGB_PIXELSIZE   3       /* JSAMPLEs pfr RGB sdbnlinf flfmfnt */


/* Dffinitions for spffd-rflbtfd optimizbtions. */


/* If your dompilfr supports inlinf fundtions, dffinf INLINE
 * bs tif inlinf kfyword; otifrwisf dffinf it bs fmpty.
 */

#ifndff INLINE
#ifdff __GNUC__                 /* for instbndf, GNU C knows bbout inlinf */
#dffinf INLINE __inlinf__
#fndif
#ifndff INLINE
#dffinf INLINE                  /* dffbult is to dffinf it bs fmpty */
#fndif
#fndif


/* On somf mbdiinfs (notbbly 68000 sfrifs) "int" is 32 bits, but multiplying
 * two 16-bit siorts is fbstfr tibn multiplying two ints.  Dffinf MULTIPLIER
 * bs siort on sudi b mbdiinf.  MULTIPLIER must bf bt lfbst 16 bits widf.
 */

#ifndff MULTIPLIER
#dffinf MULTIPLIER  int         /* typf for fbstfst intfgfr multiply */
#fndif


/* FAST_FLOAT siould bf fitifr flobt or doublf, wiidifvfr is donf fbstfr
 * by your dompilfr.  (Notf tibt tiis typf is only usfd in tif flobting point
 * DCT routinfs, so it only mbttfrs if you'vf dffinfd DCT_FLOAT_SUPPORTED.)
 * Typidblly, flobt is fbstfr in ANSI C dompilfrs, wiilf doublf is fbstfr in
 * prf-ANSI dompilfrs (bfdbusf tify insist on donvfrting to doublf bnywby).
 * Tif dodf bflow tifrfforf dioosfs flobt if wf ibvf ANSI-stylf prototypfs.
 */

#ifndff FAST_FLOAT
#ifdff HAVE_PROTOTYPES
#dffinf FAST_FLOAT  flobt
#flsf
#dffinf FAST_FLOAT  doublf
#fndif
#fndif

#fndif /* JPEG_INTERNAL_OPTIONS */
