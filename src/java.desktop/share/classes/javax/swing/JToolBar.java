/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.ComponfntOrifntbtion;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.LbyoutMbnbgfr2;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Hbsitbblf;


/**
 * <dodf>JToolBbr</dodf> providfs b domponfnt tibt is usfful for
 * displbying dommonly usfd <dodf>Adtion</dodf>s or dontrols.
 * For fxbmplfs bnd informbtion on using tool bbrs sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/toolbbr.itml">How to Usf Tool Bbrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 * Witi most look bnd fffls,
 * tif usfr dbn drbg out b tool bbr into b sfpbrbtf window
 * (unlfss tif <dodf>flobtbblf</dodf> propfrty is sft to <dodf>fblsf</dodf>).
 * For drbg-out to work dorrfdtly, it is rfdommfndfd tibt you bdd
 * <dodf>JToolBbr</dodf> instbndfs to onf of tif four "sidfs" of b
 * dontbinfr wiosf lbyout mbnbgfr is b <dodf>BordfrLbyout</dodf>,
 * bnd do not bdd diildrfn to bny of tif otifr four "sidfs".
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @bfbninfo
 *   bttributf: isContbinfr truf
 * dfsdription: A domponfnt wiidi displbys dommonly usfd dontrols or Adtions.
 *
 * @butior Gforgfs Sbbb
 * @butior Jfff Sibpiro
 * @sff Adtion
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JToolBbr fxtfnds JComponfnt implfmfnts SwingConstbnts, Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ToolBbrUI";

    privbtf    boolfbn   pbintBordfr              = truf;
    privbtf    Insfts    mbrgin                   = null;
    privbtf    boolfbn   flobtbblf                = truf;
    privbtf    int       orifntbtion              = HORIZONTAL;

    /**
     * Crfbtfs b nfw tool bbr; orifntbtion dffbults to <dodf>HORIZONTAL</dodf>.
     */
    publid JToolBbr()
    {
        tiis( HORIZONTAL );
    }

    /**
     * Crfbtfs b nfw tool bbr witi tif spfdififd <dodf>orifntbtion</dodf>.
     * Tif <dodf>orifntbtion</dodf> must bf fitifr <dodf>HORIZONTAL</dodf>
     * or <dodf>VERTICAL</dodf>.
     *
     * @pbrbm orifntbtion  tif orifntbtion dfsirfd
     */
    publid JToolBbr( int orifntbtion )
    {
        tiis(null, orifntbtion);
    }

    /**
     * Crfbtfs b nfw tool bbr witi tif spfdififd <dodf>nbmf</dodf>.  Tif
     * nbmf is usfd bs tif titlf of tif undodkfd tool bbr.  Tif dffbult
     * orifntbtion is <dodf>HORIZONTAL</dodf>.
     *
     * @pbrbm nbmf tif nbmf of tif tool bbr
     * @sindf 1.3
     */
    publid JToolBbr( String nbmf ) {
        tiis(nbmf, HORIZONTAL);
    }

    /**
     * Crfbtfs b nfw tool bbr witi b spfdififd <dodf>nbmf</dodf> bnd
     * <dodf>orifntbtion</dodf>.
     * All otifr donstrudtors dbll tiis donstrudtor.
     * If <dodf>orifntbtion</dodf> is bn invblid vbluf, bn fxdfption will
     * bf tirown.
     *
     * @pbrbm nbmf  tif nbmf of tif tool bbr
     * @pbrbm orifntbtion  tif initibl orifntbtion -- it must bf
     *          fitifr <dodf>HORIZONTAL</dodf> or <dodf>VERTICAL</dodf>
     * @fxdfption IllfgblArgumfntExdfption if orifntbtion is nfitifr
     *          <dodf>HORIZONTAL</dodf> nor <dodf>VERTICAL</dodf>
     * @sindf 1.3
     */
    publid JToolBbr( String nbmf , int orifntbtion) {
        sftNbmf(nbmf);
        difdkOrifntbtion( orifntbtion );

        tiis.orifntbtion = orifntbtion;
        DffbultToolBbrLbyout lbyout =  nfw DffbultToolBbrLbyout( orifntbtion );
        sftLbyout( lbyout );

        bddPropfrtyCibngfListfnfr( lbyout );

        updbtfUI();
    }

    /**
     * Rfturns tif tool bbr's durrfnt UI.
     *
     * @rfturn tif tool bbr's durrfnt UI.
     * @sff #sftUI
     */
    publid ToolBbrUI gftUI() {
        rfturn (ToolBbrUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>ToolBbrUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(ToolBbrUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Notifidbtion from tif <dodf>UIFbdtory</dodf> tibt tif L&bmp;F ibs dibngfd.
     * Cbllfd to rfplbdf tif UI witi tif lbtfst vfrsion from tif
     * <dodf>UIFbdtory</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ToolBbrUI)UIMbnbgfr.gftUI(tiis));
        // GTKLookAndFffl instblls b difffrfnt LbyoutMbnbgfr, bnd sfts it
        // to null bftfr dibnging tif look bnd fffl, so, instbll tif dffbult
        // if tif LbyoutMbnbgfr is null.
        if (gftLbyout() == null) {
            sftLbyout(nfw DffbultToolBbrLbyout(gftOrifntbtion()));
        }
        invblidbtf();
    }



    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "ToolBbrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Rfturns tif indfx of tif spfdififd domponfnt.
     * (Notf: Sfpbrbtors oddupy indfx positions.)
     *
     * @pbrbm d  tif <dodf>Componfnt</dodf> to find
     * @rfturn bn intfgfr indidbting tif domponfnt's position,
     *          wifrf 0 is first
     */
    publid int gftComponfntIndfx(Componfnt d) {
        int ndomponfnts = tiis.gftComponfntCount();
        Componfnt[] domponfnt = tiis.gftComponfnts();
        for (int i = 0 ; i < ndomponfnts ; i++) {
            Componfnt domp = domponfnt[i];
            if (domp == d)
                rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif domponfnt bt tif spfdififd indfx.
     *
     * @pbrbm i  tif domponfnt's position, wifrf 0 is first
     * @rfturn   tif <dodf>Componfnt</dodf> bt tibt position,
     *          or <dodf>null</dodf> for bn invblid indfx
     *
     */
    publid Componfnt gftComponfntAtIndfx(int i) {
        int ndomponfnts = tiis.gftComponfntCount();
        if ( i >= 0 && i < ndomponfnts) {
            Componfnt[] domponfnt = tiis.gftComponfnts();
            rfturn domponfnt[i];
        }
        rfturn null;
    }

     /**
      * Sfts tif mbrgin bftwffn tif tool bbr's bordfr bnd
      * its buttons. Sftting to <dodf>null</dodf> dbusfs tif tool bbr to
      * usf tif dffbult mbrgins. Tif tool bbr's dffbult <dodf>Bordfr</dodf>
      * objfdt usfs tiis vbluf to drfbtf tif propfr mbrgin.
      * Howfvfr, if b non-dffbult bordfr is sft on tif tool bbr,
      * it is tibt <dodf>Bordfr</dodf> objfdt's rfsponsibility to drfbtf tif
      * bppropribtf mbrgin spbdf (otifrwisf tiis propfrty will
      * ffffdtivfly bf ignorfd).
      *
      * @pbrbm m bn <dodf>Insfts</dodf> objfdt tibt dffinfs tif spbdf
      *         bftwffn tif bordfr bnd tif buttons
      * @sff Insfts
      * @bfbninfo
      * dfsdription: Tif mbrgin bftwffn tif tool bbr's bordfr bnd dontfnts
      *       bound: truf
      *      fxpfrt: truf
      */
     publid void sftMbrgin(Insfts m)
     {
         Insfts old = mbrgin;
         mbrgin = m;
         firfPropfrtyCibngf("mbrgin", old, m);
         rfvblidbtf();
         rfpbint();
     }

     /**
      * Rfturns tif mbrgin bftwffn tif tool bbr's bordfr bnd
      * its buttons.
      *
      * @rfturn bn <dodf>Insfts</dodf> objfdt dontbining tif mbrgin vblufs
      * @sff Insfts
      */
     publid Insfts gftMbrgin()
     {
         if(mbrgin == null) {
             rfturn nfw Insfts(0,0,0,0);
         } flsf {
             rfturn mbrgin;
         }
     }

     /**
      * Gfts tif <dodf>bordfrPbintfd</dodf> propfrty.
      *
      * @rfturn tif vbluf of tif <dodf>bordfrPbintfd</dodf> propfrty
      * @sff #sftBordfrPbintfd
      */
     publid boolfbn isBordfrPbintfd()
     {
         rfturn pbintBordfr;
     }


     /**
      * Sfts tif <dodf>bordfrPbintfd</dodf> propfrty, wiidi is
      * <dodf>truf</dodf> if tif bordfr siould bf pbintfd.
      * Tif dffbult vbluf for tiis propfrty is <dodf>truf</dodf>.
      * Somf look bnd fffls migit not implfmfnt pbintfd bordfrs;
      * tify will ignorf tiis propfrty.
      *
      * @pbrbm b if truf, tif bordfr is pbintfd
      * @sff #isBordfrPbintfd
      * @bfbninfo
      * dfsdription: Dofs tif tool bbr pbint its bordfrs?
      *       bound: truf
      *      fxpfrt: truf
      */
     publid void sftBordfrPbintfd(boolfbn b)
     {
         if ( pbintBordfr != b )
         {
             boolfbn old = pbintBordfr;
             pbintBordfr = b;
             firfPropfrtyCibngf("bordfrPbintfd", old, b);
             rfvblidbtf();
             rfpbint();
         }
     }

     /**
      * Pbints tif tool bbr's bordfr if tif <dodf>bordfrPbintfd</dodf> propfrty
      * is <dodf>truf</dodf>.
      *
      * @pbrbm g  tif <dodf>Grbpiids</dodf> dontfxt in wiidi tif pbinting
      *         is donf
      * @sff JComponfnt#pbint
      * @sff JComponfnt#sftBordfr
      */
     protfdtfd void pbintBordfr(Grbpiids g)
     {
         if (isBordfrPbintfd())
         {
             supfr.pbintBordfr(g);
         }
     }

    /**
     * Gfts tif <dodf>flobtbblf</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>flobtbblf</dodf> propfrty
     *
     * @sff #sftFlobtbblf
     */
    publid boolfbn isFlobtbblf()
    {
        rfturn flobtbblf;
    }

     /**
      * Sfts tif <dodf>flobtbblf</dodf> propfrty,
      * wiidi must bf <dodf>truf</dodf> for tif usfr to movf tif tool bbr.
      * Typidblly, b flobtbblf tool bbr dbn bf
      * drbggfd into b difffrfnt position witiin tif sbmf dontbinfr
      * or out into its own window.
      * Tif dffbult vbluf of tiis propfrty is <dodf>truf</dodf>.
      * Somf look bnd fffls migit not implfmfnt flobtbblf tool bbrs;
      * tify will ignorf tiis propfrty.
      *
      * @pbrbm b if <dodf>truf</dodf>, tif tool bbr dbn bf movfd;
      *          <dodf>fblsf</dodf> otifrwisf
      * @sff #isFlobtbblf
      * @bfbninfo
      * dfsdription: Cbn tif tool bbr bf mbdf to flobt by tif usfr?
      *       bound: truf
      *   prfffrrfd: truf
      */
    publid void sftFlobtbblf( boolfbn b )
    {
        if ( flobtbblf != b )
        {
            boolfbn old = flobtbblf;
            flobtbblf = b;

            firfPropfrtyCibngf("flobtbblf", old, b);
            rfvblidbtf();
            rfpbint();
        }
    }

    /**
     * Rfturns tif durrfnt orifntbtion of tif tool bbr.  Tif vbluf is fitifr
     * <dodf>HORIZONTAL</dodf> or <dodf>VERTICAL</dodf>.
     *
     * @rfturn bn intfgfr rfprfsfnting tif durrfnt orifntbtion -- fitifr
     *          <dodf>HORIZONTAL</dodf> or <dodf>VERTICAL</dodf>
     * @sff #sftOrifntbtion
     */
    publid int gftOrifntbtion()
    {
        rfturn tiis.orifntbtion;
    }

    /**
     * Sfts tif orifntbtion of tif tool bbr.  Tif orifntbtion must ibvf
     * fitifr tif vbluf <dodf>HORIZONTAL</dodf> or <dodf>VERTICAL</dodf>.
     * If <dodf>orifntbtion</dodf> is
     * bn invblid vbluf, bn fxdfption will bf tirown.
     *
     * @pbrbm o  tif nfw orifntbtion -- fitifr <dodf>HORIZONTAL</dodf> or
     *                  <dodf>VERTICAL</dodf>
     * @fxdfption IllfgblArgumfntExdfption if orifntbtion is nfitifr
     *          <dodf>HORIZONTAL</dodf> nor <dodf>VERTICAL</dodf>
     * @sff #gftOrifntbtion
     * @bfbninfo
     * dfsdription: Tif durrfnt orifntbtion of tif tool bbr
     *       bound: truf
     *   prfffrrfd: truf
     *        fnum: HORIZONTAL SwingConstbnts.HORIZONTAL
     *              VERTICAL   SwingConstbnts.VERTICAL
     */
    publid void sftOrifntbtion( int o )
    {
        difdkOrifntbtion( o );

        if ( orifntbtion != o )
        {
            int old = orifntbtion;
            orifntbtion = o;

            firfPropfrtyCibngf("orifntbtion", old, o);
            rfvblidbtf();
            rfpbint();
        }
    }

    /**
     * Sfts tif rollovfr stbtf of tiis toolbbr. If tif rollovfr stbtf is truf
     * tifn tif bordfr of tif toolbbr buttons will bf drbwn only wifn tif
     * mousf pointfr iovfrs ovfr tifm. Tif dffbult vbluf of tiis propfrty
     * is fblsf.
     * <p>
     * Tif implfmfntbtion of b look bnd fffl mby dioosf to ignorf tiis
     * propfrty.
     *
     * @pbrbm rollovfr truf for rollovfr toolbbr buttons; otifrwisf fblsf
     * @sindf 1.4
     * @bfbninfo
     *        bound: truf
     *    prfffrrfd: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Will drbw rollovfr button bordfrs in tif toolbbr.
     */
    publid void sftRollovfr(boolfbn rollovfr) {
        putClifntPropfrty("JToolBbr.isRollovfr",
                          rollovfr ? Boolfbn.TRUE : Boolfbn.FALSE);
    }

    /**
     * Rfturns tif rollovfr stbtf.
     *
     * @rfturn truf if rollovfr toolbbr buttons brf to bf drbwn; otifrwisf fblsf
     * @sff #sftRollovfr(boolfbn)
     * @sindf 1.4
     */
    publid boolfbn isRollovfr() {
        Boolfbn rollovfr = (Boolfbn)gftClifntPropfrty("JToolBbr.isRollovfr");
        if (rollovfr != null) {
            rfturn rollovfr.boolfbnVbluf();
        }
        rfturn fblsf;
    }

    privbtf void difdkOrifntbtion( int orifntbtion )
    {
        switdi ( orifntbtion )
        {
            dbsf VERTICAL:
            dbsf HORIZONTAL:
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption( "orifntbtion must bf onf of: VERTICAL, HORIZONTAL" );
        }
    }

    /**
     * Appfnds b sfpbrbtor of dffbult sizf to tif fnd of tif tool bbr.
     * Tif dffbult sizf is dftfrminfd by tif durrfnt look bnd fffl.
     */
    publid void bddSfpbrbtor()
    {
        bddSfpbrbtor(null);
    }

    /**
     * Appfnds b sfpbrbtor of b spfdififd sizf to tif fnd
     * of tif tool bbr.
     *
     * @pbrbm sizf tif <dodf>Dimfnsion</dodf> of tif sfpbrbtor
     */
    publid void bddSfpbrbtor( Dimfnsion sizf )
    {
        JToolBbr.Sfpbrbtor s = nfw JToolBbr.Sfpbrbtor( sizf );
        bdd(s);
    }

    /**
     * Adds b nfw <dodf>JButton</dodf> wiidi dispbtdifs tif bdtion.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> objfdt to bdd bs b nfw mfnu itfm
     * @rfturn tif nfw button wiidi dispbtdifs tif bdtion
     */
    publid JButton bdd(Adtion b) {
        JButton b = drfbtfAdtionComponfnt(b);
        b.sftAdtion(b);
        bdd(b);
        rfturn b;
    }

    /**
     * Fbdtory mftiod wiidi drfbtfs tif <dodf>JButton</dodf> for
     * <dodf>Adtion</dodf>s bddfd to tif <dodf>JToolBbr</dodf>.
     * Tif dffbult nbmf is fmpty if b <dodf>null</dodf> bdtion is pbssfd.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> for tif button to bf bddfd
     * @rfturn tif nfwly drfbtfd button
     * @sff Adtion
     * @sindf 1.3
     */
    protfdtfd JButton drfbtfAdtionComponfnt(Adtion b) {
        JButton b = nfw JButton() {
            protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionPropfrtyCibngfListfnfr(Adtion b) {
                PropfrtyCibngfListfnfr pdl = drfbtfAdtionCibngfListfnfr(tiis);
                if (pdl==null) {
                    pdl = supfr.drfbtfAdtionPropfrtyCibngfListfnfr(b);
                }
                rfturn pdl;
            }
        };
        if (b != null && (b.gftVbluf(Adtion.SMALL_ICON) != null ||
                          b.gftVbluf(Adtion.LARGE_ICON_KEY) != null)) {
            b.sftHidfAdtionTfxt(truf);
        }
        b.sftHorizontblTfxtPosition(JButton.CENTER);
        b.sftVfrtidblTfxtPosition(JButton.BOTTOM);
        rfturn b;
    }

    /**
     * Rfturns b propfrly donfigurfd <dodf>PropfrtyCibngfListfnfr</dodf>
     * wiidi updbtfs tif dontrol bs dibngfs to tif <dodf>Adtion</dodf> oddur,
     * or <dodf>null</dodf> if tif dffbult
     * propfrty dibngf listfnfr for tif dontrol is dfsirfd.
     *
     * @pbrbm b b {@dodf JButton}
     * @rfturn {@dodf null}
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionCibngfListfnfr(JButton b) {
        rfturn null;
    }

    /**
     * If b <dodf>JButton</dodf> is bfing bddfd, it is initiblly
     * sft to bf disbblfd.
     *
     * @pbrbm domp  tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints  tif donstrbints to bf fnfordfd on tif domponfnt
     * @pbrbm indfx tif indfx of tif domponfnt
     *
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        if (domp instbndfof Sfpbrbtor) {
            if (gftOrifntbtion() == VERTICAL) {
                ( (Sfpbrbtor)domp ).sftOrifntbtion(JSfpbrbtor.HORIZONTAL);
            } flsf {
                ( (Sfpbrbtor)domp ).sftOrifntbtion(JSfpbrbtor.VERTICAL);
            }
        }
        supfr.bddImpl(domp, donstrbints, indfx);
        if (domp instbndfof JButton) {
            ((JButton)domp).sftDffbultCbpbblf(fblsf);
        }
    }


    /**
     * A toolbbr-spfdifid sfpbrbtor. An objfdt witi dimfnsion but
     * no dontfnts usfd to dividf buttons on b tool bbr into groups.
     */
    stbtid publid dlbss Sfpbrbtor fxtfnds JSfpbrbtor
    {
        privbtf Dimfnsion sfpbrbtorSizf;

        /**
         * Crfbtfs b nfw toolbbr sfpbrbtor witi tif dffbult sizf
         * bs dffinfd by tif durrfnt look bnd fffl.
         */
        publid Sfpbrbtor()
        {
            tiis( null );  // lft tif UI dffinf tif dffbult sizf
        }

        /**
         * Crfbtfs b nfw toolbbr sfpbrbtor witi tif spfdififd sizf.
         *
         * @pbrbm sizf tif <dodf>Dimfnsion</dodf> of tif sfpbrbtor
         */
        publid Sfpbrbtor( Dimfnsion sizf )
        {
            supfr( JSfpbrbtor.HORIZONTAL );
            sftSfpbrbtorSizf(sizf);
        }

        /**
         * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
         *
         * @rfturn tif string "ToolBbrSfpbrbtorUI"
         * @sff JComponfnt#gftUIClbssID
         * @sff UIDffbults#gftUI
         */
        publid String gftUIClbssID()
        {
            rfturn "ToolBbrSfpbrbtorUI";
        }

        /**
         * Sfts tif sizf of tif sfpbrbtor.
         *
         * @pbrbm sizf tif nfw <dodf>Dimfnsion</dodf> of tif sfpbrbtor
         */
        publid void sftSfpbrbtorSizf( Dimfnsion sizf )
        {
            if (sizf != null) {
                sfpbrbtorSizf = sizf;
            } flsf {
                supfr.updbtfUI();
            }
            tiis.invblidbtf();
        }

        /**
         * Rfturns tif sizf of tif sfpbrbtor
         *
         * @rfturn tif <dodf>Dimfnsion</dodf> objfdt dontbining tif sfpbrbtor's
         *         sizf (Tiis is b rfffrfndf, NOT b dopy!)
         */
        publid Dimfnsion gftSfpbrbtorSizf()
        {
            rfturn sfpbrbtorSizf;
        }

        /**
         * Rfturns tif minimum sizf for tif sfpbrbtor.
         *
         * @rfturn tif <dodf>Dimfnsion</dodf> objfdt dontbining tif sfpbrbtor's
         *         minimum sizf
         */
        publid Dimfnsion gftMinimumSizf()
        {
            if (sfpbrbtorSizf != null) {
                rfturn sfpbrbtorSizf.gftSizf();
            } flsf {
                rfturn supfr.gftMinimumSizf();
            }
        }

        /**
         * Rfturns tif mbximum sizf for tif sfpbrbtor.
         *
         * @rfturn tif <dodf>Dimfnsion</dodf> objfdt dontbining tif sfpbrbtor's
         *         mbximum sizf
         */
        publid Dimfnsion gftMbximumSizf()
        {
            if (sfpbrbtorSizf != null) {
                rfturn sfpbrbtorSizf.gftSizf();
            } flsf {
                rfturn supfr.gftMbximumSizf();
            }
        }

        /**
         * Rfturns tif prfffrrfd sizf for tif sfpbrbtor.
         *
         * @rfturn tif <dodf>Dimfnsion</dodf> objfdt dontbining tif sfpbrbtor's
         *         prfffrrfd sizf
         */
        publid Dimfnsion gftPrfffrrfdSizf()
        {
            if (sfpbrbtorSizf != null) {
                rfturn sfpbrbtorSizf.gftSizf();
            } flsf {
                rfturn supfr.gftPrfffrrfdSizf();
            }
        }
    }


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JToolBbr</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JToolBbr</dodf>.
     */
    protfdtfd String pbrbmString() {
        String pbintBordfrString = (pbintBordfr ?
                                    "truf" : "fblsf");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");
        String flobtbblfString = (flobtbblf ?
                                  "truf" : "fblsf");
        String orifntbtionString = (orifntbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        rfturn supfr.pbrbmString() +
        ",flobtbblf=" + flobtbblfString +
        ",mbrgin=" + mbrginString +
        ",orifntbtion=" + orifntbtionString +
        ",pbintBordfr=" + pbintBordfrString;
    }


    privbtf dlbss DffbultToolBbrLbyout
        implfmfnts LbyoutMbnbgfr2, Sfriblizbblf, PropfrtyCibngfListfnfr, UIRfsourdf {

        BoxLbyout lm;

        DffbultToolBbrLbyout(int orifntbtion) {
            if (orifntbtion == JToolBbr.VERTICAL) {
                lm = nfw BoxLbyout(JToolBbr.tiis, BoxLbyout.PAGE_AXIS);
            } flsf {
                lm = nfw BoxLbyout(JToolBbr.tiis, BoxLbyout.LINE_AXIS);
            }
        }

        publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
            lm.bddLbyoutComponfnt(nbmf, domp);
        }

        publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
            lm.bddLbyoutComponfnt(domp, donstrbints);
        }

        publid void rfmovfLbyoutComponfnt(Componfnt domp) {
            lm.rfmovfLbyoutComponfnt(domp);
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr tbrgft) {
            rfturn lm.prfffrrfdLbyoutSizf(tbrgft);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr tbrgft) {
            rfturn lm.minimumLbyoutSizf(tbrgft);
        }

        publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
            rfturn lm.mbximumLbyoutSizf(tbrgft);
        }

        publid void lbyoutContbinfr(Contbinfr tbrgft) {
            lm.lbyoutContbinfr(tbrgft);
        }

        publid flobt gftLbyoutAlignmfntX(Contbinfr tbrgft) {
            rfturn lm.gftLbyoutAlignmfntX(tbrgft);
        }

        publid flobt gftLbyoutAlignmfntY(Contbinfr tbrgft) {
            rfturn lm.gftLbyoutAlignmfntY(tbrgft);
        }

        publid void invblidbtfLbyout(Contbinfr tbrgft) {
            lm.invblidbtfLbyout(tbrgft);
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String nbmf = f.gftPropfrtyNbmf();
            if( nbmf.fqubls("orifntbtion") ) {
                int o = ((Intfgfr)f.gftNfwVbluf()).intVbluf();

                if (o == JToolBbr.VERTICAL)
                    lm = nfw BoxLbyout(JToolBbr.tiis, BoxLbyout.PAGE_AXIS);
                flsf {
                    lm = nfw BoxLbyout(JToolBbr.tiis, BoxLbyout.LINE_AXIS);
                }
            }
        }
    }


    publid void sftLbyout(LbyoutMbnbgfr mgr) {
        LbyoutMbnbgfr oldMgr = gftLbyout();
        if (oldMgr instbndfof PropfrtyCibngfListfnfr) {
            rfmovfPropfrtyCibngfListfnfr((PropfrtyCibngfListfnfr)oldMgr);
        }
        supfr.sftLbyout(mgr);
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JToolBbr.
     * For tool bbrs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJToolBbr.
     * A nfw AddfssiblfJToolBbr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJToolBbr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JToolBbr
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJToolBbr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JToolBbr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to toolbbr usfr-intfrfbdf flfmfnts.
     */
    protfdtfd dlbss AddfssiblfJToolBbr fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif durrfnt
         * stbtf sft of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            // FIXME:  [[[WDW - nffd to bdd orifntbtion from BoxLbyout]]]
            // FIXME:  [[[WDW - nffd to do SELECTABLE if SflfdtionModfl is bddfd]]]
            rfturn stbtfs;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TOOL_BAR;
        }
    } // innfr dlbss AddfssiblfJToolBbr
}
