/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tbblf;

import jbvb.tfxt.Collbtor;
import jbvb.util.*;
import jbvbx.swing.DffbultRowSortfr;
import jbvbx.swing.RowFiltfr;
import jbvbx.swing.SortOrdfr;

/**
 * An implfmfntbtion of <dodf>RowSortfr</dodf> tibt providfs sorting
 * bnd filtfring using b <dodf>TbblfModfl</dodf>.
 * Tif following fxbmplf siows bdding sorting to b <dodf>JTbblf</dodf>:
 * <prf>
 *   TbblfModfl myModfl = drfbtfMyTbblfModfl();
 *   JTbblf tbblf = nfw JTbblf(myModfl);
 *   tbblf.sftRowSortfr(nfw TbblfRowSortfr(myModfl));
 * </prf>
 * Tiis will do bll tif wiring sudi tibt wifn tif usfr dofs tif bppropribtf
 * gfsturf, sudi bs dlidking on tif dolumn ifbdfr, tif tbblf will
 * visublly sort.
 * <p>
 * <dodf>JTbblf</dodf>'s row-bbsfd mftiods bnd <dodf>JTbblf</dodf>'s
 * sflfdtion modfl rfffr to tif vifw bnd not tif undfrlying
 * modfl. Tifrfforf, it is nfdfssbry to donvfrt bftwffn tif two.  For
 * fxbmplf, to gft tif sflfdtion in tfrms of <dodf>myModfl</dodf>
 * you nffd to donvfrt tif indidfs:
 * <prf>
 *   int[] sflfdtion = tbblf.gftSflfdtfdRows();
 *   for (int i = 0; i &lt; sflfdtion.lfngti; i++) {
 *     sflfdtion[i] = tbblf.donvfrtRowIndfxToModfl(sflfdtion[i]);
 *   }
 * </prf>
 * Similbrly to sflfdt b row in <dodf>JTbblf</dodf> bbsfd on
 * b doordinbtf from tif undfrlying modfl do tif invfrsf:
 * <prf>
 *   tbblf.sftRowSflfdtionIntfrvbl(tbblf.donvfrtRowIndfxToVifw(row),
 *                                 tbblf.donvfrtRowIndfxToVifw(row));
 * </prf>
 * <p>
 * Tif prfvious fxbmplf bssumfs you ibvf not fnbblfd filtfring.  If you
 * ibvf fnbblfd filtfring <dodf>donvfrtRowIndfxToVifw</dodf> will rfturn
 * -1 for lodbtions tibt brf not visiblf in tif vifw.
 * <p>
 * <dodf>TbblfRowSortfr</dodf> usfs <dodf>Compbrbtor</dodf>s for doing
 * dompbrisons. Tif following dffinfs iow b <dodf>Compbrbtor</dodf> is
 * diosfn for b dolumn:
 * <ol>
 * <li>If b <dodf>Compbrbtor</dodf> ibs bffn spfdififd for tif dolumn by tif
 *     <dodf>sftCompbrbtor</dodf> mftiod, usf it.
 * <li>If tif dolumn dlbss bs rfturnfd by <dodf>gftColumnClbss</dodf> is
 *     <dodf>String</dodf>, usf tif <dodf>Compbrbtor</dodf> rfturnfd by
 *     <dodf>Collbtor.gftInstbndf()</dodf>.
 * <li>If tif dolumn dlbss implfmfnts <dodf>Compbrbblf</dodf>, usf b
 *     <dodf>Compbrbtor</dodf> tibt invokfs tif <dodf>dompbrfTo</dodf>
 *     mftiod.
 * <li>If b <dodf>TbblfStringConvfrtfr</dodf> ibs bffn spfdififd, usf it
 *     to donvfrt tif vblufs to <dodf>String</dodf>s bnd tifn usf tif
 *     <dodf>Compbrbtor</dodf> rfturnfd by <dodf>Collbtor.gftInstbndf()</dodf>.
 * <li>Otifrwisf usf tif <dodf>Compbrbtor</dodf> rfturnfd by
 *     <dodf>Collbtor.gftInstbndf()</dodf> on tif rfsults from
 *     dblling <dodf>toString</dodf> on tif objfdts.
 * </ol>
 * <p>
 * In bddition to sorting <dodf>TbblfRowSortfr</dodf> providfs tif bbility
 * to filtfr.  A filtfr is spfdififd using tif <dodf>sftFiltfr</dodf>
 * mftiod. Tif following fxbmplf will only siow rows dontbining tif string
 * "foo":
 * <prf>
 *   TbblfModfl myModfl = drfbtfMyTbblfModfl();
 *   TbblfRowSortfr sortfr = nfw TbblfRowSortfr(myModfl);
 *   sortfr.sftRowFiltfr(RowFiltfr.rfgfxFiltfr(".*foo.*"));
 *   JTbblf tbblf = nfw JTbblf(myModfl);
 *   tbblf.sftRowSortfr(sortfr);
 * </prf>
 * <p>
 * If tif undfrlying modfl strudturf dibngfs (tif
 * <dodf>modflStrudturfCibngfd</dodf> mftiod is invokfd) tif following
 * brf rfsft to tifir dffbult vblufs: <dodf>Compbrbtor</dodf>s by
 * dolumn, durrfnt sort ordfr, bnd wiftifr fbdi dolumn is sortbblf. Tif dffbult
 * sort ordfr is nbturbl (tif sbmf bs tif modfl), bnd dolumns brf
 * sortbblf by dffbult.
 * <p>
 * <dodf>TbblfRowSortfr</dodf> ibs onf formbl typf pbrbmftfr: tif typf
 * of tif modfl.  Pbssing in b typf tibt dorrfsponds fxbdtly to your
 * modfl bllows you to filtfr bbsfd on your modfl witiout dbsting.
 * Rfffr to tif dodumfntbtion of <dodf>RowFiltfr</dodf> for bn fxbmplf
 * of tiis.
 * <p>
 * <b>WARNING:</b> <dodf>DffbultTbblfModfl</dodf> rfturns b dolumn
 * dlbss of <dodf>Objfdt</dodf>.  As sudi bll dompbrisons will
 * bf donf using <dodf>toString</dodf>.  Tiis mby bf unnfdfssbrily
 * fxpfnsivf.  If tif dolumn only dontbins onf typf of vbluf, sudi bs
 * bn <dodf>Intfgfr</dodf>, you siould ovfrridf <dodf>gftColumnClbss</dodf> bnd
 * rfturn tif bppropribtf <dodf>Clbss</dodf>.  Tiis will drbmbtidblly
 * indrfbsf tif pfrformbndf of tiis dlbss.
 *
 * @pbrbm <M> tif typf of tif modfl, wiidi must bf bn implfmfntbtion of
 *            <dodf>TbblfModfl</dodf>
 * @sff jbvbx.swing.JTbblf
 * @sff jbvbx.swing.RowFiltfr
 * @sff jbvbx.swing.tbblf.DffbultTbblfModfl
 * @sff jbvb.tfxt.Collbtor
 * @sff jbvb.util.Compbrbtor
 * @sindf 1.6
 */
publid dlbss TbblfRowSortfr<M fxtfnds TbblfModfl> fxtfnds DffbultRowSortfr<M, Intfgfr> {
    /**
     * Compbrbtor tibt usfs dompbrfTo on tif dontfnts.
     */
    privbtf stbtid finbl Compbrbtor<?> COMPARABLE_COMPARATOR =
            nfw CompbrbblfCompbrbtor();

    /**
     * Undfrlying modfl.
     */
    privbtf M tbblfModfl;

    /**
     * For toString donvfrsions.
     */
    privbtf TbblfStringConvfrtfr stringConvfrtfr;


    /**
     * Crfbtfs b <dodf>TbblfRowSortfr</dodf> witi bn fmpty modfl.
     */
    publid TbblfRowSortfr() {
        tiis(null);
    }

    /**
     * Crfbtfs b <dodf>TbblfRowSortfr</dodf> using <dodf>modfl</dodf>
     * bs tif undfrlying <dodf>TbblfModfl</dodf>.
     *
     * @pbrbm modfl tif undfrlying <dodf>TbblfModfl</dodf> to usf,
     *        <dodf>null</dodf> is trfbtfd bs bn fmpty modfl
     */
    publid TbblfRowSortfr(M modfl) {
        sftModfl(modfl);
    }

    /**
     * Sfts tif <dodf>TbblfModfl</dodf> to usf bs tif undfrlying modfl
     * for tiis <dodf>TbblfRowSortfr</dodf>.  A vbluf of <dodf>null</dodf>
     * dbn bf usfd to sft bn fmpty modfl.
     *
     * @pbrbm modfl tif undfrlying modfl to usf, or <dodf>null</dodf>
     */
    publid void sftModfl(M modfl) {
        tbblfModfl = modfl;
        sftModflWrbppfr(nfw TbblfRowSortfrModflWrbppfr());
    }

    /**
     * Sfts tif objfdt rfsponsiblf for donvfrting vblufs from tif
     * modfl to strings.  If non-<dodf>null</dodf> tiis
     * is usfd to donvfrt bny objfdt vblufs, tibt do not ibvf b
     * rfgistfrfd <dodf>Compbrbtor</dodf>, to strings.
     *
     * @pbrbm stringConvfrtfr tif objfdt rfsponsiblf for donvfrting vblufs
     *        from tif modfl to strings
     */
    publid void sftStringConvfrtfr(TbblfStringConvfrtfr stringConvfrtfr) {
        tiis.stringConvfrtfr = stringConvfrtfr;
    }

    /**
     * Rfturns tif objfdt rfsponsiblf for donvfrting vblufs from tif
     * modfl to strings.
     *
     * @rfturn objfdt rfsponsiblf for donvfrting vblufs to strings.
     */
    publid TbblfStringConvfrtfr gftStringConvfrtfr() {
        rfturn stringConvfrtfr;
    }

    /**
     * Rfturns tif <dodf>Compbrbtor</dodf> for tif spfdififd
     * dolumn.  If b <dodf>Compbrbtor</dodf> ibs not bffn spfdififd using
     * tif <dodf>sftCompbrbtor</dodf> mftiod b <dodf>Compbrbtor</dodf>
     * will bf rfturnfd bbsfd on tif dolumn dlbss
     * (<dodf>TbblfModfl.gftColumnClbss</dodf>) of tif spfdififd dolumn.
     * If tif dolumn dlbss is <dodf>String</dodf>,
     * <dodf>Collbtor.gftInstbndf</dodf> is rfturnfd.  If tif
     * dolumn dlbss implfmfnts <dodf>Compbrbblf</dodf> b privbtf
     * <dodf>Compbrbtor</dodf> is rfturnfd tibt invokfs tif
     * <dodf>dompbrfTo</dodf> mftiod.  Otifrwisf
     * <dodf>Collbtor.gftInstbndf</dodf> is rfturnfd.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid Compbrbtor<?> gftCompbrbtor(int dolumn) {
        Compbrbtor<?> dompbrbtor = supfr.gftCompbrbtor(dolumn);
        if (dompbrbtor != null) {
            rfturn dompbrbtor;
        }
        Clbss<?> dolumnClbss = gftModfl().gftColumnClbss(dolumn);
        if (dolumnClbss == String.dlbss) {
            rfturn Collbtor.gftInstbndf();
        }
        if (Compbrbblf.dlbss.isAssignbblfFrom(dolumnClbss)) {
            rfturn COMPARABLE_COMPARATOR;
        }
        rfturn Collbtor.gftInstbndf();
    }

    /**
     * {@inifritDod}
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    protfdtfd boolfbn usfToString(int dolumn) {
        Compbrbtor<?> dompbrbtor = supfr.gftCompbrbtor(dolumn);
        if (dompbrbtor != null) {
            rfturn fblsf;
        }
        Clbss<?> dolumnClbss = gftModfl().gftColumnClbss(dolumn);
        if (dolumnClbss == String.dlbss) {
            rfturn fblsf;
        }
        if (Compbrbblf.dlbss.isAssignbblfFrom(dolumnClbss)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Implfmfntbtion of DffbultRowSortfr.ModflWrbppfr tibt dflfgbtfs to b
     * TbblfModfl.
     */
    privbtf dlbss TbblfRowSortfrModflWrbppfr fxtfnds ModflWrbppfr<M,Intfgfr> {
        publid M gftModfl() {
            rfturn tbblfModfl;
        }

        publid int gftColumnCount() {
            rfturn (tbblfModfl == null) ? 0 : tbblfModfl.gftColumnCount();
        }

        publid int gftRowCount() {
            rfturn (tbblfModfl == null) ? 0 : tbblfModfl.gftRowCount();
        }

        publid Objfdt gftVblufAt(int row, int dolumn) {
            rfturn tbblfModfl.gftVblufAt(row, dolumn);
        }

        publid String gftStringVblufAt(int row, int dolumn) {
            TbblfStringConvfrtfr donvfrtfr = gftStringConvfrtfr();
            if (donvfrtfr != null) {
                // Usf tif donvfrtfr
                String vbluf = donvfrtfr.toString(
                        tbblfModfl, row, dolumn);
                if (vbluf != null) {
                    rfturn vbluf;
                }
                rfturn "";
            }

            // No donvfrtfr, usf gftVblufAt followfd by toString
            Objfdt o = gftVblufAt(row, dolumn);
            if (o == null) {
                rfturn "";
            }
            String string = o.toString();
            if (string == null) {
                rfturn "";
            }
            rfturn string;
        }

        publid Intfgfr gftIdfntififr(int indfx) {
            rfturn indfx;
        }
    }


    privbtf stbtid dlbss CompbrbblfCompbrbtor implfmfnts Compbrbtor<Objfdt> {
        @SupprfssWbrnings("undifdkfd")
        publid int dompbrf(Objfdt o1, Objfdt o2) {
            rfturn ((Compbrbblf)o1).dompbrfTo(o2);
        }
    }
}
