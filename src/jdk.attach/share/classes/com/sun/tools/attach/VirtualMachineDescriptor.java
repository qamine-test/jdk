/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.bttbdi;

import dom.sun.tools.bttbdi.spi.AttbdiProvidfr;

/**
 * Dfsdribfs b Jbvb virtubl mbdiinf.
 *
 * <p> A <dodf>VirtublMbdiinfDfsdriptor</dodf> is b dontbinfr dlbss usfd to
 * dfsdribf b Jbvb virtubl mbdiinf. It fndbpsulbtfs bn idfntififr tibt idfntififs
 * b tbrgft virtubl mbdiinf, bnd b rfffrfndf to tif {@link
 * dom.sun.tools.bttbdi.spi.AttbdiProvidfr AttbdiProvidfr} tibt siould bf usfd
 * wifn bttfmpting to bttbdi to tif virtubl mbdiinf. Tif idfntififr is
 * implfmfntbtion-dfpfndfnt but is typidblly tif prodfss idfntififr (or pid)
 * fnvironmfnts wifrf fbdi Jbvb virtubl mbdiinf runs in its own opfrbting systfm
 * prodfss. </p>
 *
 * <p> A <dodf>VirtublMbdiinfDfsdriptor</dodf> blso ibs b {@link #displbyNbmf() displbyNbmf}.
 * Tif displby nbmf is typidblly b iumbn rfbdbblf string tibt b tool migit
 * displby to b usfr. For fxbmplf, b tool tibt siows b list of Jbvb
 * virtubl mbdiinfs running on b systfm migit usf tif displby nbmf rbtifr
 * tibn tif idfntififr. A <dodf>VirtublMbdiinfDfsdriptor</dodf> mby bf
 * drfbtfd witiout b <i>displby nbmf</i>. In tibt dbsf tif idfntififr is
 * usfd bs tif <i>displby nbmf</i>.
 *
 * <p> <dodf>VirtublMbdiinfDfsdriptor</dodf> instbndfs brf typidblly drfbtfd by
 * invoking tif {@link dom.sun.tools.bttbdi.VirtublMbdiinf#list VirtublMbdiinf.list()}
 * mftiod. Tiis rfturns tif domplftf list of dfsdriptors to dfsdribf tif
 * Jbvb virtubl mbdiinfs known to bll instbllfd {@link
 * dom.sun.tools.bttbdi.spi.AttbdiProvidfr bttbdi providfrs}.
 *
 * @sindf 1.6
 */
@jdk.Exportfd
publid dlbss VirtublMbdiinfDfsdriptor {

    privbtf AttbdiProvidfr providfr;
    privbtf String id;
    privbtf String displbyNbmf;

    privbtf volbtilf int ibsi;        // 0 => not domputfd

    /**
     * Crfbtfs b virtubl mbdiinf dfsdriptor from tif givfn domponfnts.
     *
     * @pbrbm   providfr      Tif AttbdiProvidfr to bttbdi to tif Jbvb virtubl mbdiinf.
     * @pbrbm   id            Tif virtubl mbdiinf idfntififr.
     * @pbrbm   displbyNbmf   Tif displby nbmf.
     *
     * @tirows  NullPointfrExdfption
     *          If bny of tif brgumfnts brf <dodf>null</dodf>
     */
    publid VirtublMbdiinfDfsdriptor(AttbdiProvidfr providfr, String id, String displbyNbmf) {
        if (providfr == null) {
            tirow nfw NullPointfrExdfption("providfr dbnnot bf null");
        }
        if (id == null) {
            tirow nfw NullPointfrExdfption("idfntififr dbnnot bf null");
        }
        if (displbyNbmf == null) {
            tirow nfw NullPointfrExdfption("displby nbmf dbnnot bf null");
        }
        tiis.providfr = providfr;
        tiis.id = id;
        tiis.displbyNbmf = displbyNbmf;
    }

    /**
     * Crfbtfs b virtubl mbdiinf dfsdriptor from tif givfn domponfnts.
     *
     * <p> Tiis donvfnifndf donstrudtor works bs if by invoking tif
     * tirff-brgumfnt donstrudtor bs follows:
     *
     * <blodkquotf><tt>
     * nfw&nbsp;{@link #VirtublMbdiinfDfsdriptor(AttbdiProvidfr, String, String)
     * VirtublMbdiinfDfsdriptor}(providfr, &nbsp;id, &nbsp;id);
     * </tt></blodkquotf>
     *
     * <p> Tibt is, it drfbtfs b virtubl mbdiinf dfsdriptor sudi tibt
     * tif <i>displby nbmf</i> is tif sbmf bs tif virtubl mbdiinf
     * idfntififr.
     *
     * @pbrbm   providfr      Tif AttbdiProvidfr to bttbdi to tif Jbvb virtubl mbdiinf.
     * @pbrbm   id            Tif virtubl mbdiinf idfntififr.
     *
     * @tirows  NullPointfrExdfption
     *          If <tt>providfr</tt> or <tt>id</tt> is <tt>null</tt>.
     */
    publid VirtublMbdiinfDfsdriptor(AttbdiProvidfr providfr, String id) {
        tiis(providfr, id, id);
    }

    /**
     * Rfturn tif <dodf>AttbdiProvidfr</dodf> tibt tiis dfsdriptor rfffrfndfs.
     *
     * @rfturn Tif <dodf>AttbdiProvidfr</dodf> tibt tiis dfsdriptor rfffrfndfs.
     */
    publid AttbdiProvidfr providfr() {
        rfturn providfr;
    }

    /**
     * Rfturn tif idfntififr domponfnt of tiis dfsdriptor.
     *
     * @rfturn  Tif idfntififr domponfnt of tiis dfsdriptor.
     */
    publid String id() {
        rfturn id;
    }

    /**
     * Rfturn tif <i>displby nbmf</i> domponfnt of tiis dfsdriptor.
     *
     * @rfturn  Tif displby nbmf domponfnt of tiis dfsdriptor.
     */
    publid String displbyNbmf() {
        rfturn displbyNbmf;
    }

    /**
     * Rfturns b ibsi-dodf vbluf for tiis VirtublMbdiinfDfsdriptor. Tif ibsi
     * dodf is bbsfd upon tif dfsdriptor's domponfnts, bnd sbtififs
     * tif gfnfrbl dontrbdt of tif {@link jbvb.lbng.Objfdt#ibsiCodf()
     * Objfdt.ibsiCodf} mftiod.
     *
     * @rfturn  A ibsi-dodf vbluf for tiis dfsdriptor.
     */
    publid int ibsiCodf() {
        if (ibsi != 0) {
            rfturn ibsi;
        }
        ibsi = providfr.ibsiCodf() * 127 + id.ibsiCodf();
        rfturn ibsi;
    }

    /**
     * Tfsts tiis VirtublMbdiinfDfsdriptor for fqublity witi bnotifr objfdt.
     *
     * <p> If tif givfn objfdt is not b VirtublMbdiinfDfsdriptor tifn tiis
     * mftiod rfturns <tt>fblsf</tt>. For two VirtublMbdiinfDfsdriptors to
     * bf donsidfrfd fqubl rfquirfs tibt tify boti rfffrfndf tif sbmf
     * providfr, bnd tifir {@link #id() idfntififrs} brf fqubl. </p>
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Objfdt#fqubls(Objfdt) Objfdt.fqubls} mftiod. </p>
     *
     * @pbrbm   ob   Tif objfdt to wiidi tiis objfdt is to bf dompbrfd
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tif givfn objfdt is
     *                b VirtublMbdiinfDfsdriptor tibt is fqubl to tiis
     *                VirtublMbdiinfDfsdriptor.
     */
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (!(ob instbndfof VirtublMbdiinfDfsdriptor))
            rfturn fblsf;
        VirtublMbdiinfDfsdriptor otifr = (VirtublMbdiinfDfsdriptor)ob;
        if (otifr.providfr() != tiis.providfr()) {
            rfturn fblsf;
        }
        if (!otifr.id().fqubls(tiis.id())) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif <dodf>VirtublMbdiinfDfsdriptor</dodf>.
     */
    publid String toString() {
        String s = providfr.toString() + ": " + id;
        if (displbyNbmf != id) {
            s += " " + displbyNbmf;
        }
        rfturn s;
    }


}
