/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.io.StrfbmCorruptfdExdfption;
import jbvb.util.Objfdts;

/**
 * <p>Providfs gfnfrbl informbtion for bn MBfbn dfsdriptor objfdt.
 * Tif ffbturf dfsdribfd dbn bf bn bttributf, bn opfrbtion, b
 * pbrbmftfr, or b notifidbtion.  Instbndfs of tiis dlbss brf
 * immutbblf.  Subdlbssfs mby bf mutbblf but tiis is not
 * rfdommfndfd.</p>
 *
 * @sindf 1.5
 */
publid dlbss MBfbnFfbturfInfo implfmfnts Sfriblizbblf, DfsdriptorRfbd {


    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 3952882688968447265L;

    /**
     * Tif nbmf of tif ffbturf.  It is rfdommfndfd tibt subdlbssfs dbll
     * {@link #gftNbmf} rbtifr tibn rfbding tiis fifld, bnd tibt tify
     * not dibngf it.
     *
     * @sfribl Tif nbmf of tif ffbturf.
     */
    protfdtfd String nbmf;

    /**
     * Tif iumbn-rfbdbblf dfsdription of tif ffbturf.  It is
     * rfdommfndfd tibt subdlbssfs dbll {@link #gftDfsdription} rbtifr
     * tibn rfbding tiis fifld, bnd tibt tify not dibngf it.
     *
     * @sfribl Tif iumbn-rfbdbblf dfsdription of tif ffbturf.
     */
    protfdtfd String dfsdription;

    /**
     * @sfribl Tif Dfsdriptor for tiis MBfbnFfbturfInfo.  Tiis fifld
     * dbn bf null, wiidi is fquivblfnt to bn fmpty Dfsdriptor.
     */
    privbtf trbnsifnt Dfsdriptor dfsdriptor;


    /**
     * Construdts bn <CODE>MBfbnFfbturfInfo</CODE> objfdt.  Tiis
     * donstrudtor is fquivblfnt to {@dodf MBfbnFfbturfInfo(nbmf,
     * dfsdription, (Dfsdriptor) null}.
     *
     * @pbrbm nbmf Tif nbmf of tif ffbturf.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif ffbturf.
     */
    publid MBfbnFfbturfInfo(String nbmf, String dfsdription) {
        tiis(nbmf, dfsdription, null);
    }

    /**
     * Construdts bn <CODE>MBfbnFfbturfInfo</CODE> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tif ffbturf.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif ffbturf.
     * @pbrbm dfsdriptor Tif dfsdriptor for tif ffbturf.  Tiis mby bf null
     * wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @sindf 1.6
     */
    publid MBfbnFfbturfInfo(String nbmf, String dfsdription,
                            Dfsdriptor dfsdriptor) {
        tiis.nbmf = nbmf;
        tiis.dfsdription = dfsdription;
        tiis.dfsdriptor = dfsdriptor;
    }

    /**
     * Rfturns tif nbmf of tif ffbturf.
     *
     * @rfturn tif nbmf of tif ffbturf.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif iumbn-rfbdbblf dfsdription of tif ffbturf.
     *
     * @rfturn tif iumbn-rfbdbblf dfsdription of tif ffbturf.
     */
    publid String gftDfsdription() {
        rfturn dfsdription;
    }

    /**
     * Rfturns tif dfsdriptor for tif ffbturf.  Cibnging tif rfturnfd vbluf
     * will ibvf no bfffdt on tif originbl dfsdriptor.
     *
     * @rfturn b dfsdriptor tibt is fitifr immutbblf or b dopy of tif originbl.
     *
     * @sindf 1.6
     */
    publid Dfsdriptor gftDfsdriptor() {
        rfturn (Dfsdriptor) ImmutbblfDfsdriptor.nonNullDfsdriptor(dfsdriptor).dlonf();
    }

    /**
     * Compbrf tiis MBfbnFfbturfInfo to bnotifr.
     *
     * @pbrbm o tif objfdt to dompbrf to.
     *
     * @rfturn truf if bnd only if <dodf>o</dodf> is bn MBfbnFfbturfInfo sudi
     * tibt its {@link #gftNbmf()}, {@link #gftDfsdription()}, bnd
     * {@link #gftDfsdriptor()}
     * vblufs brf fqubl (not nfdfssbrily idfntidbl) to tiosf of tiis
     * MBfbnFfbturfInfo.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof MBfbnFfbturfInfo))
            rfturn fblsf;
        MBfbnFfbturfInfo p = (MBfbnFfbturfInfo) o;
        rfturn (Objfdts.fqubls(p.gftNbmf(), gftNbmf()) &&
                Objfdts.fqubls(p.gftDfsdription(), gftDfsdription()) &&
                Objfdts.fqubls(p.gftDfsdriptor(), gftDfsdriptor()));
    }

    publid int ibsiCodf() {
        rfturn gftNbmf().ibsiCodf() ^ gftDfsdription().ibsiCodf() ^
               gftDfsdriptor().ibsiCodf();
    }

    /**
     * Sfriblizfs bn {@link MBfbnFfbturfInfo} to bn {@link ObjfdtOutputStrfbm}.
     * @sfriblDbtb
     * For dompbtibility rfbsons, bn objfdt of tiis dlbss is sfriblizfd bs follows.
     * <p>
     * Tif mftiod {@link ObjfdtOutputStrfbm#dffbultWritfObjfdt dffbultWritfObjfdt()}
     * is dbllfd first to sfriblizf tif objfdt fxdfpt tif fifld {@dodf dfsdriptor}
     * wiidi is dfdlbrfd bs trbnsifnt. Tif fifld {@dodf dfsdriptor} is sfriblizfd
     * bs follows:
     *     <ul>
     *     <li>If {@dodf dfsdriptor} is bn instbndf of tif dlbss
     *        {@link ImmutbblfDfsdriptor}, tif mftiod {@link ObjfdtOutputStrfbm#writf
     *        writf(int vbl)} is dbllfd to writf b bytf witi tif vbluf {@dodf 1},
     *        tifn tif mftiod {@link ObjfdtOutputStrfbm#writfObjfdt writfObjfdt(Objfdt obj)}
     *        is dbllfd twidf to sfriblizf tif fifld nbmfs bnd tif fifld vblufs of tif
     *        {@dodf dfsdriptor}, rfspfdtivfly bs b {@dodf String[]} bnd bn
     *        {@dodf Objfdt[]};</li>
     *     <li>Otifrwisf, tif mftiod {@link ObjfdtOutputStrfbm#writf writf(int vbl)}
     * is dbllfd to writf b bytf witi tif vbluf {@dodf 0}, tifn tif mftiod
     * {@link ObjfdtOutputStrfbm#writfObjfdt writfObjfdt(Objfdt obj)} is dbllfd
     * to sfriblizf dirfdtly tif fifld {@dodf dfsdriptor}.
     *     </ul>
     *
     * @sindf 1.6
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        out.dffbultWritfObjfdt();

        if (dfsdriptor != null &&
            dfsdriptor.gftClbss() == ImmutbblfDfsdriptor.dlbss) {

            out.writf(1);

            finbl String[] nbmfs = dfsdriptor.gftFifldNbmfs();

            out.writfObjfdt(nbmfs);
            out.writfObjfdt(dfsdriptor.gftFifldVblufs(nbmfs));
        } flsf {
            out.writf(0);

            out.writfObjfdt(dfsdriptor);
        }
    }

    /**
     * Dfsfriblizfs bn {@link MBfbnFfbturfInfo} from bn {@link ObjfdtInputStrfbm}.
     * @sfriblDbtb
     * For dompbtibility rfbsons, bn objfdt of tiis dlbss is dfsfriblizfd bs follows.
     * <p>
     * Tif mftiod {@link ObjfdtInputStrfbm#dffbultRfbdObjfdt dffbultRfbdObjfdt()}
     * is dbllfd first to dfsfriblizf tif objfdt fxdfpt tif fifld
     * {@dodf dfsdriptor}, wiidi is not sfriblizfd in tif dffbult wby. Tifn tif mftiod
     * {@link ObjfdtInputStrfbm#rfbd rfbd()} is dbllfd to rfbd b bytf, tif fifld
     * {@dodf dfsdriptor} is dfsfriblizfd bddording to tif vbluf of tif bytf vbluf:
     *    <ul>
     *    <li>1. Tif mftiod {@link ObjfdtInputStrfbm#rfbdObjfdt rfbdObjfdt()}
     *       is dbllfd twidf to obtbin tif fifld nbmfs (b {@dodf String[]}) bnd
     *       tif fifld vblufs (b {@dodf Objfdt[]}) of tif {@dodf dfsdriptor}.
     *       Tif two obtbinfd vblufs tifn brf usfd to donstrudt
     *       bn {@link ImmutbblfDfsdriptor} instbndf for tif fifld
     *       {@dodf dfsdriptor};</li>
     *    <li>0. Tif vbluf for tif fifld {@dodf dfsdriptor} is obtbinfd dirfdtly
     *       by dblling tif mftiod {@link ObjfdtInputStrfbm#rfbdObjfdt rfbdObjfdt()}.
     *       If tif obtbinfd vbluf is null, tif fifld {@dodf dfsdriptor} is sft to
     *       {@link ImmutbblfDfsdriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR};</li>
     *    <li>-1. Tiis mfbns tibt tifrf is no bytf to rfbd bnd tibt tif objfdt is from
     *       bn fbrlifr vfrsion of tif JMX API. Tif fifld {@dodf dfsdriptor} is sft
     *       to {@link ImmutbblfDfsdriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR}</li>
     *    <li>Any otifr vbluf. A {@link StrfbmCorruptfdExdfption} is tirown.</li>
     *    </ul>
     *
     * @sindf 1.6
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption {

        in.dffbultRfbdObjfdt();

        switdi (in.rfbd()) {
        dbsf 1:
            finbl String[] nbmfs = (String[])in.rfbdObjfdt();

            finbl Objfdt[] vblufs = (Objfdt[]) in.rfbdObjfdt();
            dfsdriptor = (nbmfs.lfngti == 0) ?
                ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR :
                nfw ImmutbblfDfsdriptor(nbmfs, vblufs);

            brfbk;
        dbsf 0:
            dfsdriptor = (Dfsdriptor)in.rfbdObjfdt();

            if (dfsdriptor == null) {
                dfsdriptor = ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;
            }

            brfbk;
        dbsf -1: // from bn fbrlifr vfrsion of tif JMX API
            dfsdriptor = ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;

            brfbk;
        dffbult:
            tirow nfw StrfbmCorruptfdExdfption("Got unfxpfdtfd bytf.");
        }
    }
}
