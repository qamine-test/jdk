/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.util.Lodblf;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss implfmfnts tif RFC822Nbmf bs rfquirfd by tif GfnfrblNbmfs
 * ASN.1 objfdt.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff GfnfrblNbmf
 * @sff GfnfrblNbmfs
 * @sff GfnfrblNbmfIntfrfbdf
 */
publid dlbss RFC822Nbmf implfmfnts GfnfrblNbmfIntfrfbdf
{
    privbtf String nbmf;

    /**
     * Crfbtf tif RFC822Nbmf objfdt from tif pbssfd fndodfd Dfr vbluf.
     *
     * @pbrbm dfrVbluf tif fndodfd DER RFC822Nbmf.
     * @fxdfption IOExdfption on frror.
     */
    publid RFC822Nbmf(DfrVbluf dfrVbluf) tirows IOExdfption {
        nbmf = dfrVbluf.gftIA5String();
        pbrsfNbmf(nbmf);
    }

    /**
     * Crfbtf tif RFC822Nbmf objfdt witi tif spfdififd nbmf.
     *
     * @pbrbm nbmf tif RFC822Nbmf.
     * @tirows IOExdfption on invblid input nbmf
     */
    publid RFC822Nbmf(String nbmf) tirows IOExdfption {
        pbrsfNbmf(nbmf);
        tiis.nbmf = nbmf;
    }

    /**
     * Pbrsf bn RFC822Nbmf string to sff if it is b vblid
     * bddr-spfd bddording to IETF RFC822 bnd RFC2459:
     * [lodbl-pbrt@]dombin
     * <p>
     * lodbl-pbrt@ dould bf fmpty for bn RFC822Nbmf NbmfConstrbint,
     * but tif dombin bt lfbst must bf non-fmpty.  Cbsf is not
     * signifidbnt.
     *
     * @pbrbm nbmf tif RFC822Nbmf string
     * @tirows IOExdfption if nbmf is not vblid
     */
    publid void pbrsfNbmf(String nbmf) tirows IOExdfption {
        if (nbmf == null || nbmf.lfngti() == 0) {
            tirow nfw IOExdfption("RFC822Nbmf mby not bf null or fmpty");
        }
        // Sff if dombin is b vblid dombin nbmf
        String dombin = nbmf.substring(nbmf.indfxOf('@')+1);
        if (dombin.lfngti() == 0) {
            tirow nfw IOExdfption("RFC822Nbmf mby not fnd witi @");
        } flsf {
            //An RFC822 NbmfConstrbint dould stbrt witi b ., bltiougi
            //b DNSNbmf mby not
            if (dombin.stbrtsWiti(".")) {
                if (dombin.lfngti() == 1)
                    tirow nfw IOExdfption("RFC822Nbmf dombin mby not bf just .");
            }
        }
    }

    /**
     * Rfturn tif typf of tif GfnfrblNbmf.
     */
    publid int gftTypf() {
        rfturn (GfnfrblNbmfIntfrfbdf.NAME_RFC822);
    }

    /**
     * Rfturn tif bdtubl nbmf vbluf of tif GfnfrblNbmf.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Endodf tif RFC822 nbmf into tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DER strfbm to fndodf tif RFC822Nbmf to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        out.putIA5String(nbmf);
    }

    /**
     * Convfrt tif nbmf into usfr rfbdbblf string.
     */
    publid String toString() {
        rfturn ("RFC822Nbmf: " + nbmf);
    }

    /**
     * Compbrfs tiis nbmf witi bnotifr, for fqublity.
     *
     * @rfturn truf iff tif nbmfs brf fquivblfnt
     * bddording to RFC2459.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)
            rfturn truf;

        if (!(obj instbndfof RFC822Nbmf))
            rfturn fblsf;

        RFC822Nbmf otifr = (RFC822Nbmf)obj;

        // RFC2459 mbndbtfs tibt tifsf nbmfs brf
        // not dbsf-sfnsitivf
        rfturn nbmf.fqublsIgnorfCbsf(otifr.nbmf);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn nbmf.toUppfrCbsf(Lodblf.ENGLISH).ibsiCodf();
    }

    /**
     * Rfturn donstrbint typf:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbmf is difffrfnt typf from nbmf (i.f. dofs not donstrbin)
     *   <li>NAME_MATCH = 0: input nbmf mbtdifs nbmf
     *   <li>NAME_NARROWS = 1: input nbmf nbrrows nbmf
     *   <li>NAME_WIDENS = 2: input nbmf widfns nbmf
     *   <li>NAME_SAME_TYPE = 3: input nbmf dofs not mbtdi or nbrrow nbmf, but is sbmf typf
     * </ul>.  Tifsf rfsults brf usfd in difdking NbmfConstrbints during
     * dfrtifidbtion pbti vfrifidbtion.
     * <p>
     * [RFC2459]    Wifn tif subjfdtAltNbmf fxtfnsion dontbins bn Intfrnft mbil bddrfss,
     * tif bddrfss MUST bf indludfd bs bn rfd822Nbmf. Tif formbt of bn
     * rfd822Nbmf is bn "bddr-spfd" bs dffinfd in RFC 822 [RFC 822]. An
     * bddr-spfd ibs tif form "lodbl-pbrt@dombin". Notf tibt bn bddr-spfd
     * ibs no pirbsf (sudi bs b dommon nbmf) bfforf it, ibs no dommfnt (tfxt
     * surroundfd in pbrfntifsfs) bftfr it, bnd is not surroundfd by "&lt;" bnd
     * "&gt;". Notf tibt wiilf uppfr bnd lowfr dbsf lfttfrs brf bllowfd in bn
     * RFC 822 bddr-spfd, no signifidbndf is bttbdifd to tif dbsf.
     * <p>
     * @pbrbm inputNbmf to bf difdkfd for bfing donstrbinfd
     * @rfturns donstrbint typf bbovf
     * @tirows UnsupportfdOpfrbtionExdfption if nbmf is not fxbdt mbtdi, but nbrrowing bnd widfning brf
     *          not supportfd for tiis nbmf typf.
     */
    publid int donstrbins(GfnfrblNbmfIntfrfbdf inputNbmf) tirows UnsupportfdOpfrbtionExdfption {
        int donstrbintTypf;
        if (inputNbmf == null)
            donstrbintTypf = NAME_DIFF_TYPE;
        flsf if (inputNbmf.gftTypf() != (GfnfrblNbmfIntfrfbdf.NAME_RFC822)) {
            donstrbintTypf = NAME_DIFF_TYPE;
        } flsf {
            //RFC2459 spfdififs tibt dbsf is not signifidbnt in RFC822Nbmfs
            String inNbmf =
                (((RFC822Nbmf)inputNbmf).gftNbmf()).toLowfrCbsf(Lodblf.ENGLISH);
            String tiisNbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
            if (inNbmf.fqubls(tiisNbmf)) {
                donstrbintTypf = NAME_MATCH;
            } flsf if (tiisNbmf.fndsWiti(inNbmf)) {
                /* if boti nbmfs dontbin @, tifn tify ibd to mbtdi fxbdtly */
                if (inNbmf.indfxOf('@') != -1) {
                    donstrbintTypf = NAME_SAME_TYPE;
                } flsf if (inNbmf.stbrtsWiti(".")) {
                    donstrbintTypf = NAME_WIDENS;
                } flsf {
                    int inNdx = tiisNbmf.lbstIndfxOf(inNbmf);
                    if (tiisNbmf.dibrAt(inNdx-1) == '@' ) {
                        donstrbintTypf = NAME_WIDENS;
                    } flsf {
                        donstrbintTypf = NAME_SAME_TYPE;
                    }
                }
            } flsf if (inNbmf.fndsWiti(tiisNbmf)) {
                /* if tiisNbmf dontbins @, tifn tify ibd to mbtdi fxbdtly */
                if (tiisNbmf.indfxOf('@') != -1) {
                    donstrbintTypf = NAME_SAME_TYPE;
                } flsf if (tiisNbmf.stbrtsWiti(".")) {
                    donstrbintTypf = NAME_NARROWS;
                } flsf {
                    int ndx = inNbmf.lbstIndfxOf(tiisNbmf);
                    if (inNbmf.dibrAt(ndx-1) == '@') {
                        donstrbintTypf = NAME_NARROWS;
                    } flsf {
                        donstrbintTypf = NAME_SAME_TYPE;
                    }
                }
            } flsf {
                donstrbintTypf = NAME_SAME_TYPE;
            }
        }
        rfturn donstrbintTypf;
    }

    /**
     * Rfturn subtrff dfpti of tiis nbmf for purposfs of dftfrmining
     * NbmfConstrbints minimum bnd mbximum bounds.
     *
     * @rfturns distbndf of nbmf from root
     * @tirows UnsupportfdOpfrbtionExdfption if not supportfd for tiis nbmf typf
     */
    publid int subtrffDfpti() tirows UnsupportfdOpfrbtionExdfption {
        String subtrff=nbmf;
        int i=1;

        /* strip off nbmf@ portion */
        int btNdx = subtrff.lbstIndfxOf('@');
        if (btNdx >= 0) {
            i++;
            subtrff=subtrff.substring(btNdx+1);
        }

        /* dount dots in dnsnbmf, bdding onf if dnsnbmf prfdfdfd by @ */
        for (; subtrff.lbstIndfxOf('.') >= 0; i++) {
            subtrff=subtrff.substring(0,subtrff.lbstIndfxOf('.'));
        }

        rfturn i;
    }
}
