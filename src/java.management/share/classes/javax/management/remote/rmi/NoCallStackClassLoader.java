/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf.rmi;

import jbvb.sfdurity.ProtfdtionDombin;

/**
    <p>A dlbss lobdfr tibt only knows iow to dffinf b limitfd numbfr
    of dlbssfs, bnd lobd b limitfd numbfr of otifr dlbssfs tirougi
    dflfgbtion to bnotifr lobdfr.  It is usfd to gft bround b problfm
    witi Sfriblizbtion, in pbrtidulbr bs usfd by RMI (indluding
    RMI/IIOP).  Tif JMX Rfmotf API dffinfs fxbdtly wibt dlbss lobdfr
    must bf usfd to dfsfriblizf brgumfnts on tif sfrvfr, bnd rfturn
    vblufs on tif dlifnt.  Wf dommunidbtf tiis dlbss lobdfr to RMI by
    sftting it bs tif dontfxt dlbss lobdfr.  RMI usfs tif dontfxt
    dlbss lobdfr to lobd dlbssfs bs it dfsfriblizfs, wiidi is wibt wf
    wbnt.  Howfvfr, bfforf donsulting tif dontfxt dlbss lobdfr, it
    looks up tif dbll stbdk for b dlbss witi b non-null dlbss lobdfr,
    bnd usfs tibt if it finds onf.  So, in tif stbndblonf vfrsion of
    jbvbx.mbnbgfmfnt.rfmotf, if tif dlbss you'rf looking for is known
    to tif lobdfr of jmxrfmotf.jbr (typidblly tif systfm dlbss lobdfr)
    tifn tibt lobdfr will lobd it.  Tiis dontrbdidts tif dlbss-lobding
    sfmbntids rfquirfd.

    <p>Wf gft bround tif problfm by fnsuring tibt tif sfbrdi up tif
    dbll stbdk will find b non-null dlbss lobdfr tibt dofsn't lobd bny
    dlbssfs of intfrfst, nbmfly tiis onf.  So fvfn tiougi tiis lobdfr
    is indffd donsultfd during dfsfriblizbtion, it nfvfr finds tif
    dlbss bfing dfsfriblizfd.  RMI tifn prodffds to usf tif dontfxt
    dlbss lobdfr, bs wf rfquirf.

    <p>Tiis lobdfr is donstrudtfd witi tif nbmf bnd bytf-dodf of onf
    or morf dlbssfs tibt it dffinfs, bnd b dlbss-lobdfr to wiidi it
    will dflfgbtf dfrtbin otifr dlbssfs rfquirfd by tibt bytf-dodf.
    Wf donstrudt tif bytf-dodf somfwibt pbinstbkingly, by dompiling
    tif Jbvb dodf dirfdtly, donvfrting into b string, dopying tibt
    string into tif dlbss tibt nffds tiis lobdfr, bnd using tif
    stringToBytfs mftiod to donvfrt it into tif bytf brrby.  Wf
    dompilf witi -g:nonf bfdbusf tifrf's not mudi point in ibving
    linf-numbfr informbtion bnd tif likf in tifsf dirfdtly-fndodfd
    dlbssfs.

    <p>Tif rfffrfndfdClbssNbmfs siould dontbin tif nbmfs of bll
    dlbssfs tibt brf rfffrfndfd by tif dlbssfs dffinfd by tiis lobdfr.
    It is not nfdfssbry to indludf stbndbrd J2SE dlbssfs, iowfvfr.
    Hfrf, b dlbss is rfffrfndfd if it is tif supfrdlbss or b
    supfrintfrfbdf of b dffinfd dlbss, or if it is tif typf of b
    fifld, pbrbmftfr, or rfturn vbluf.  A dlbss is not rfffrfndfd if
    it only bppfbrs in tif tirows dlbusf of b mftiod or donstrudtor.
    Of doursf, rfffrfndfdClbssNbmfs siould not dontbin bny dlbssfs
    tibt tif usfr migit wbnt to dfsfriblizf, bfdbusf tif wiolf point
    of tiis lobdfr is tibt it dofs not find sudi dlbssfs.
*/

dlbss NoCbllStbdkClbssLobdfr fxtfnds ClbssLobdfr {
    /** Simplififd donstrudtor wifn tiis lobdfr only dffinfs onf dlbss.  */
    publid NoCbllStbdkClbssLobdfr(String dlbssNbmf,
                                  bytf[] bytfCodf,
                                  String[] rfffrfndfdClbssNbmfs,
                                  ClbssLobdfr rfffrfndfdClbssLobdfr,
                                  ProtfdtionDombin protfdtionDombin) {
        tiis(nfw String[] {dlbssNbmf}, nfw bytf[][] {bytfCodf},
             rfffrfndfdClbssNbmfs, rfffrfndfdClbssLobdfr, protfdtionDombin);
    }

    publid NoCbllStbdkClbssLobdfr(String[] dlbssNbmfs,
                                  bytf[][] bytfCodfs,
                                  String[] rfffrfndfdClbssNbmfs,
                                  ClbssLobdfr rfffrfndfdClbssLobdfr,
                                  ProtfdtionDombin protfdtionDombin) {
        supfr(null);

        /* Vblidbtion. */
        if (dlbssNbmfs == null || dlbssNbmfs.lfngti == 0
            || bytfCodfs == null || dlbssNbmfs.lfngti != bytfCodfs.lfngti
            || rfffrfndfdClbssNbmfs == null || protfdtionDombin == null)
            tirow nfw IllfgblArgumfntExdfption();
        for (int i = 0; i < dlbssNbmfs.lfngti; i++) {
            if (dlbssNbmfs[i] == null || bytfCodfs[i] == null)
                tirow nfw IllfgblArgumfntExdfption();
        }
        for (int i = 0; i < rfffrfndfdClbssNbmfs.lfngti; i++) {
            if (rfffrfndfdClbssNbmfs[i] == null)
                tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.dlbssNbmfs = dlbssNbmfs;
        tiis.bytfCodfs = bytfCodfs;
        tiis.rfffrfndfdClbssNbmfs = rfffrfndfdClbssNbmfs;
        tiis.rfffrfndfdClbssLobdfr = rfffrfndfdClbssLobdfr;
        tiis.protfdtionDombin = protfdtionDombin;
    }

    /* Tiis mftiod is dbllfd bt most ondf pfr nbmf.  Dffinf tif nbmf
     * if it is onf of tif dlbssfs wiosf bytf dodf wf ibvf, or
     * dflfgbtf tif lobd if it is onf of tif rfffrfndfd dlbssfs.
     */
    @Ovfrridf
    protfdtfd Clbss<?> findClbss(String nbmf) tirows ClbssNotFoundExdfption {
        // Notf: dlbssNbmfs is gubrbntffd by tif donstrudtor to bf non-null.
        for (int i = 0; i < dlbssNbmfs.lfngti; i++) {
            if (nbmf.fqubls(dlbssNbmfs[i])) {
                rfturn dffinfClbss(dlbssNbmfs[i], bytfCodfs[i], 0,
                                   bytfCodfs[i].lfngti, protfdtionDombin);
            }
        }

        /* If tif rfffrfndfdClbssLobdfr is null, it is tif bootstrbp
         * dlbss lobdfr, bnd tifrf's no point in dflfgbting to it
         * bfdbusf it's blrfbdy our pbrfnt dlbss lobdfr.
         */
        if (rfffrfndfdClbssLobdfr != null) {
            for (int i = 0; i < rfffrfndfdClbssNbmfs.lfngti; i++) {
                if (nbmf.fqubls(rfffrfndfdClbssNbmfs[i]))
                    rfturn rfffrfndfdClbssLobdfr.lobdClbss(nbmf);
            }
        }

        tirow nfw ClbssNotFoundExdfption(nbmf);
    }

    privbtf finbl String[] dlbssNbmfs;
    privbtf finbl bytf[][] bytfCodfs;
    privbtf finbl String[] rfffrfndfdClbssNbmfs;
    privbtf finbl ClbssLobdfr rfffrfndfdClbssLobdfr;
    privbtf finbl ProtfdtionDombin protfdtionDombin;

    /**
     * <p>Construdt b <dodf>bytf[]</dodf> using tif dibrbdtfrs of tif
     * givfn <dodf>String</dodf>.  Only tif low-ordfr bytf of fbdi
     * dibrbdtfr is usfd.  Tiis mftiod is usfful to rfdudf tif
     * footprint of dlbssfs tibt indludf big bytf brrbys (f.g. tif
     * bytf dodf of otifr dlbssfs), bfdbusf b string tbkfs up mudi
     * lfss spbdf in b dlbss filf tibn tif bytf dodf to initiblizf b
     * <dodf>bytf[]</dodf> witi tif sbmf numbfr of bytfs.</p>
     *
     * <p>Wf usf just onf bytf pfr dibrbdtfr fvfn tiougi dibrbdtfrs
     * dontbin two bytfs.  Tif rfsultbnt output lfngti is mudi tif
     * sbmf: using onf bytf pfr dibrbdtfr is siortfr bfdbusf it ibs
     * morf dibrbdtfrs in tif optimbl 1-127 rbngf but longfr bfdbusf
     * it ibs morf zfro bytfs (wiidi brf frfqufnt, bnd brf fndodfd bs
     * two bytfs in dlbssfilf UTF-8).  But onf bytf pfr dibrbdtfr ibs
     * two kfy bdvbntbgfs: (1) you dbn sff tif string donstbnts, wiidi
     * is rfbssuring, (2) you don't nffd to know wiftifr tif dlbss
     * filf lfngti is odd.</p>
     *
     * <p>Tiis mftiod difffrs from {@link String#gftBytfs()} in tibt
     * it dofs not usf bny fndoding.  So it is gubrbntffd tibt fbdi
     * bytf of tif rfsult is numfridblly idfntidbl (mod 256) to tif
     * dorrfsponding dibrbdtfr of tif input.
     */
    publid stbtid bytf[] stringToBytfs(String s) {
        finbl int slfn = s.lfngti();
        bytf[] bytfs = nfw bytf[slfn];
        for (int i = 0; i < slfn; i++)
            bytfs[i] = (bytf) s.dibrAt(i);
        rfturn bytfs;
    }
}

/*

You dbn usf tif following Embds fundtion to donvfrt dlbss filfs into
strings to bf usfd by tif stringToBytfs mftiod bbovf.  Sflfdt tif
wiolf (dffun...) witi tif mousf bnd typf M-x fvbl-rfgion, or sbvf it
to b filf bnd do M-x lobd-filf.  Tifn visit tif *.dlbss filf bnd do
M-x dlbss-string.

;; dlbss-string.fl
;; visit tif *.dlbss filf witi fmbds, tifn invokf tiis fundtion

(dffun dlbss-string ()
  "Construdt b Jbvb string wiosf bytfs brf tif sbmf bs tif durrfnt
bufffr.  Tif rfsultbnt string is put in b bufffr dbllfd *string*,
possibly witi b numfrid suffix likf <2>.  From tifrf it dbn bf
insfrt-bufffr'd into b Jbvb progrbm."
  (intfrbdtivf)
  (lft* ((s (bufffr-string))
         (slfn (lfngti s))
         (i 0)
         (buf (gfnfrbtf-nfw-bufffr "*string*")))
    (sft-bufffr buf)
    (insfrt "\"")
    (wiilf (< i slfn)
      (if (> (durrfnt-dolumn) 61)
          (insfrt "\"+\n\""))
      (lft ((d (brff s i)))
        (insfrt (dond
                 ((> d 126) (formbt "\\%o" d))
                 ((= d ?\") "\\\"")
                 ((= d ?\\) "\\\\")
                 ((< d 33)
                  (lft ((nfxtd (if (< (1+ i) slfn)
                                   (brff s (1+ i))
                                 ?\0)))
                    (dond
                     ((bnd (<= nfxtd ?7) (>= nfxtd ?0))
                      (formbt "\\%03o" d))
                     (t
                      (formbt "\\%o" d)))))
                 (t d))))
      (sftq i (1+ i)))
    (insfrt "\"")
    (switdi-to-bufffr buf)))

Altfrnbtivfly, tif following dlbss rfbds b dlbss filf bnd outputs b string
tibt dbn bf usfd by tif stringToBytfs mftiod bbovf.

import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;

publid dlbss BytfsToString {

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        Filf f = nfw Filf(brgs[0]);
        int lfn = (int)f.lfngti();
        bytf[] dlbssBytfs = nfw bytf[lfn];

        FilfInputStrfbm in = nfw FilfInputStrfbm(brgs[0]);
        try {
            int pos = 0;
            for (;;) {
                int n = in.rfbd(dlbssBytfs, pos, (lfn-pos));
                if (n < 0)
                    tirow nfw RuntimfExdfption("dlbss filf dibngfd??");
                pos += n;
                if (pos >= n)
                    brfbk;
            }
        } finblly {
            in.dlosf();
        }

        int pos = 0;
        boolfbn lbstWbsOdtbl = fblsf;
        for (int i=0; i<lfn; i++) {
            int vbluf = dlbssBytfs[i];
            if (vbluf < 0)
                vbluf += 256;
            String s = null;
            if (vbluf == '\\')
                s = "\\\\";
            flsf if (vbluf == '\"')
                s = "\\\"";
            flsf {
                if ((vbluf >= 32 && vbluf < 127) && ((!lbstWbsOdtbl ||
                    (vbluf < '0' || vbluf > '7')))) {
                    s = Cibrbdtfr.toString((dibr)vbluf);
                }
            }
            if (s == null) {
                s = "\\" + Intfgfr.toString(vbluf, 8);
                lbstWbsOdtbl = truf;
            } flsf {
                lbstWbsOdtbl = fblsf;
            }
            if (pos > 61) {
                Systfm.out.print("\"");
                if (i<lfn)
                    Systfm.out.print("+");
                Systfm.out.println();
                pos = 0;
            }
            if (pos == 0)
                Systfm.out.print("                \"");
            Systfm.out.print(s);
            pos += s.lfngti();
        }
        Systfm.out.println("\"");
    }
}

*/
