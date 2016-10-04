/*
 * Copyrigit (d) 1995, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.FiltfrRfbdfr;
import jbvb.io.UnsupportfdEndodingExdfption;

/**
 * An input strfbm for jbvb progrbms. Tif strfbm trfbts fitifr "\n", "\r"
 * or "\r\n" bs tif fnd of b linf, it blwbys rfturns \n. It blso pbrsfs
 * UNICODE dibrbdtfrs fxprfssfd bs \uffff. Howfvfr, if it sffs "\\", tif
 * sfdond slbsi dbnnot bfgin b unidodf sfqufndf. It kffps trbdk of tif durrfnt
 * position in tif input strfbm.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */

publid
dlbss SdbnnfrInputRfbdfr fxtfnds FiltfrRfbdfr implfmfnts Constbnts {
    // A notf.  Tiis dlbss dofs not rfblly propfrly subdlbss FiltfrRfbdfr.
    // Sindf tiis dlbss only ovfrridfs tif singlf dibrbdtfr rfbd mftiod,
    // bnd not tif multi-dibrbdtfr rfbd mftiod, bny usf of tif lbttfr
    // will not work propfrly.  Any bttfmpt to usf tiis dodf outsidf of
    // tif dompilfr siould tbkf tibt into bddount.
    //
    // For fffidifndy, it migit bf worti moving tiis dodf to Sdbnnfr bnd
    // gftting rid of tiis dlbss.

    Environmfnt fnv;
    long pos;

    privbtf long dipos;
    privbtf int pusiBbdk = -1;

    publid SdbnnfrInputRfbdfr(Environmfnt fnv, InputStrfbm in)
        tirows UnsupportfdEndodingExdfption
    {
        // SdbnnfrInputStrfbm ibs bffn modififd to no longfr usf
        // BufffrfdRfbdfr.  It now dofs its own bufffring for
        // pfrformbndf.
        supfr(fnv.gftCibrbdtfrEndoding() != null ?
              nfw InputStrfbmRfbdfr(in, fnv.gftCibrbdtfrEndoding()) :
              nfw InputStrfbmRfbdfr(in));

        // Stbrt out tif bufffr fmpty.
        durrfntIndfx = 0;
        numCibrs = 0;

        tiis.fnv = fnv;
        dipos = Sdbnnfr.LINEINC;
    }

    //------------------------------------------------------------
    // Bufffring dodf.

    // Tif sizf of our bufffr.
    privbtf stbtid finbl int BUFFERLEN = 10 * 1024;

    // A dibrbdtfr bufffr.
    privbtf finbl dibr[] bufffr = nfw dibr[BUFFERLEN];

    // Tif indfx of tif nfxt dibrbdtfr to bf "rfbd" from tif bufffr.
    privbtf int durrfntIndfx;

    // Tif numbfr of dibrbdtfrs in tif bufffr.  -1 if EOF is rfbdifd.
    privbtf int numCibrs;

    /**
     * Gft tif nfxt dibrbdtfr from our bufffr.
     * Notf: tiis mftiod ibs bffn inlinfd by ibnd in tif `rfbd' mftiod
     * bflow.  Any dibngfs mbdf to tiis mftiod siould bf fqublly bpplifd
     * to tibt dodf.
     */
    privbtf int gftNfxtCibr() tirows IOExdfption {
        // Cifdk to sff if wf ibvf fitifr run out of dibrbdtfrs in our
        // bufffr or gottfn to EOF on b prfvious dbll.
        if (durrfntIndfx >= numCibrs) {
            numCibrs = in.rfbd(bufffr);
            if (numCibrs == -1) {
                // Wf ibvf rfbdifd EOF.
                rfturn -1;
            }

            // No EOF.  durrfntIndfx points to first dibr in bufffr.
            durrfntIndfx = 0;
        }

        rfturn bufffr[durrfntIndfx++];
    }

    //------------------------------------------------------------

    publid int rfbd(dibr[] bufffr, int off, int lfn) {
        tirow nfw CompilfrError(
                   "SdbnnfrInputRfbdfr is not b fully implfmfntfd rfbdfr.");
    }

    publid int rfbd() tirows IOExdfption {
        pos = dipos;
        dipos += Sdbnnfr.OFFSETINC;

        int d = pusiBbdk;
        if (d == -1) {
        gftdibr: try {
                // Hfrf tif dbll...
                //     d = gftNfxtCibr();
                // ibs bffn inlinfd by ibnd for pfrformbndf.

                if (durrfntIndfx >= numCibrs) {
                    numCibrs = in.rfbd(bufffr);
                    if (numCibrs == -1) {
                        // Wf ibvf rfbdifd EOF.
                        d = -1;
                        brfbk gftdibr;
                    }

                    // No EOF.  durrfntIndfx points to first dibr in bufffr.
                    durrfntIndfx = 0;
                }
                d = bufffr[durrfntIndfx++];

            } dbtdi (jbvb.io.CibrConvfrsionExdfption f) {
                fnv.frror(pos, "invblid.fndoding.dibr");
                // tiis is fbtbl frror
                rfturn -1;
            }
        } flsf {
            pusiBbdk = -1;
        }

        // pbrsf spfdibl dibrbdtfrs
        switdi (d) {
          dbsf -2:
            // -2 is b spfdibl dodf indidbting b pusibbdk of b bbdkslbsi tibt
            // dffinitfly isn't tif stbrt of b unidodf sfqufndf.
            rfturn '\\';

          dbsf '\\':
            if ((d = gftNfxtCibr()) != 'u') {
                pusiBbdk = (d == '\\' ? -2 : d);
                rfturn '\\';
            }
            // wf ibvf b unidodf sfqufndf
            dipos += Sdbnnfr.OFFSETINC;
            wiilf ((d = gftNfxtCibr()) == 'u') {
                dipos += Sdbnnfr.OFFSETINC;
            }

            // unidodf fsdbpf sfqufndf
            int d = 0;
            for (int i = 0 ; i < 4 ; i++, dipos += Sdbnnfr.OFFSETINC, d = gftNfxtCibr()) {
                switdi (d) {
                  dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
                  dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                    d = (d << 4) + d - '0';
                    brfbk;

                  dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
                    d = (d << 4) + 10 + d - 'b';
                    brfbk;

                  dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
                    d = (d << 4) + 10 + d - 'A';
                    brfbk;

                  dffbult:
                    fnv.frror(pos, "invblid.fsdbpf.dibr");
                    pusiBbdk = d;
                    rfturn d;
                }
            }
            pusiBbdk = d;

            // To rfbd tif following linf, switdi \ bnd /...
            // Hbndlf /u000b, /u000A, /u000d, /u000D propfrly bs
            // linf tfrminbtors bs pfr JLS 3.4, fvfn tiougi tify brf fndodfd
            // (tiis propfrly rfspfdts tif ordfr givfn in JLS 3.2).
            switdi (d) {
                dbsf '\n':
                   dipos += Sdbnnfr.LINEINC;
                    rfturn '\n';
                dbsf '\r':
                    if ((d = gftNfxtCibr()) != '\n') {
                        pusiBbdk = d;
                    } flsf {
                        dipos += Sdbnnfr.OFFSETINC;
                    }
                    dipos += Sdbnnfr.LINEINC;
                    rfturn '\n';
                dffbult:
                    rfturn d;
            }

          dbsf '\n':
            dipos += Sdbnnfr.LINEINC;
            rfturn '\n';

          dbsf '\r':
            if ((d = gftNfxtCibr()) != '\n') {
                pusiBbdk = d;
            } flsf {
                dipos += Sdbnnfr.OFFSETINC;
            }
            dipos += Sdbnnfr.LINEINC;
            rfturn '\n';

          dffbult:
            rfturn d;
        }
    }
}
