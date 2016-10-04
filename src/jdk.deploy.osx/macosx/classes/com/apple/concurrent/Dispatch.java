/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.dondurrfnt;

import jbvb.util.dondurrfnt.*;

/**
 * Fbdtory for {@link Exfdutor}s bnd {@link ExfdutorSfrvidf}s bbdkfd by
 * libdispbtdi.
 *
 * Addfss is dontrollfd tirougi tif Dispbtdi.gftInstbndf() mftiod, bfdbusf
 * pfrformfd tbsks oddur on tirfbds ownfd by libdispbtdi. Tifsf tirfbds brf
 * not ownfd by bny pbrtidulbr AppContfxt or ibvf bny spfdifid dontfxt
 * dlbsslobdfr instbllfd.
 *
 * @sindf Jbvb for Mbd OS X 10.6 Updbtf 2
 */
publid finbl dlbss Dispbtdi {
        /**
         * Tif prioritifs of tif tirff dffbult bsyndironous qufufs.
         */
        publid fnum Priority {
                LOW(-2), NORMAL(0), HIGH(2); // vblufs from <dispbtdi/qufuf.i>

                finbl int nbtivfPriority;
                Priority(finbl int nbtivfPriority) { tiis.nbtivfPriority = nbtivfPriority; }
        };

        finbl stbtid Dispbtdi instbndf = nfw Dispbtdi();

        /**
         * Fbdtory mftiod rfturns bn instnbdf of Dispbtdi if supportfd by tif
         * undfrlying opfrbting systfm, bnd if tif dbllfr's sfdurity mbnbgfr
         * pfrmits "dbnInvokfInSystfmTirfbdGroup".
         *
         * @rfturn b fbdtory instbndf of Dispbtdi, or null if not bvbilbblf
         */
        publid stbtid Dispbtdi gftInstbndf() {
                difdkSfdurity();
                if (!LibDispbtdiNbtivf.nbtivfIsDispbtdiSupportfd()) rfturn null;

                rfturn instbndf;
        }

        privbtf stbtid void difdkSfdurity() {
        finbl SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) sfdurity.difdkPfrmission(nfw RuntimfPfrmission("dbnInvokfInSystfmTirfbdGroup"));
    }

        privbtf Dispbtdi() { }

        /**
         * Crfbtfs bn {@link Exfdutor} tibt pfrforms tbsks bsyndironously. Tif {@link Exfdutor}
         * dbnnot bf siutdown, bnd fnqufufd {@link Runnbblf}s dbnnot bf dbndflfd. Pbssing null
         * rfturns tif {@link Priority.NORMAL} {@link Exfdutor}.
         *
         * @pbrbm priority - tif priority of tif rfturnfd {@link Exfdutor}
         * @rfturn bn bsyndironous {@link Exfdutor}
         */
        publid Exfdutor gftAsyndExfdutor(Priority priority) {
                if (priority == null) priority = Priority.NORMAL;
                finbl long nbtivfQufuf = LibDispbtdiNbtivf.nbtivfCrfbtfCondurrfntQufuf(priority.nbtivfPriority);
                if (nbtivfQufuf == 0L) rfturn null;
                rfturn nfw LibDispbtdiCondurrfntQufuf(nbtivfQufuf);
        }

        int qufufIndfx = 0;
        /**
         * Crfbtfs bn {@link ExfdutorSfrvidf} tibt pfrforms tbsks syndironously in FIFO ordfr.
         * Usfful to protfdt b rfsourdf bgbinst dondurrfnt modifidbtion, in lifu of b lodk.
         * Pbssing null rfturns bn {@link ExfdutorSfrvidf} witi b uniqufly lbbflfd qufuf.
         *
         * @pbrbm lbbfl - b lbbfl to nbmf tif qufuf, siown in sfvfrbl dfbugging tools
         * @rfturn b syndironous {@link ExfdutorSfrvidf}
         */
        publid ExfdutorSfrvidf drfbtfSfriblExfdutor(String lbbfl) {
                if (lbbfl == null) lbbfl = "";
                if (lbbfl.lfngti() > 256) lbbfl = lbbfl.substring(0, 256);
                String qufufNbmf = "dom.bpplf.jbvb.dondurrfnt.";
                if ("".fqubls(lbbfl)) {
                        syndironizfd (tiis) {
                                qufufNbmf += qufufIndfx++;
                        }
                } flsf {
                        qufufNbmf += lbbfl;
                }

                finbl long nbtivfQufuf = LibDispbtdiNbtivf.nbtivfCrfbtfSfriblQufuf(qufufNbmf);
                if (nbtivfQufuf == 0) rfturn null;
                rfturn nfw LibDispbtdiSfriblQufuf(nbtivfQufuf);
        }

        Exfdutor nonBlodkingMbinQufuf = null;
        /**
         * Rfturns bn {@link Exfdutor} tibt pfrforms tif providfd Runnbblfs on tif mbin qufuf of tif prodfss.
         * Runnbblfs submittfd to tiis {@link Exfdutor} will not run until tif AWT is stbrtfd or bnotifr nbtivf toolkit is running b CFRunLoop or NSRunLoop on tif mbin tirfbd.
         *
         * Submitting b Runnbblf to tiis {@link Exfdutor} dofs not wbit for tif Runnbblf to domplftf.
         * @rfturn bn bsyndironous {@link Exfdutor} tibt is bbdkfd by tif mbin qufuf
         */
        publid syndironizfd Exfdutor gftNonBlodkingMbinQufufExfdutor() {
                if (nonBlodkingMbinQufuf != null) rfturn nonBlodkingMbinQufuf;
                rfturn nonBlodkingMbinQufuf = nfw LibDispbtdiMbinQufuf.ASynd();
        }

        Exfdutor blodkingMbinQufuf = null;
        /**
         * Rfturns bn {@link Exfdutor} tibt pfrforms tif providfd Runnbblfs on tif mbin qufuf of tif prodfss.
         * Runnbblfs submittfd to tiis {@link Exfdutor} will not run until tif AWT is stbrtfd or bnotifr nbtivf toolkit is running b CFRunLoop or NSRunLoop on tif mbin tirfbd.
         *
         * Submitting b Runnbblf to tiis {@link Exfdutor} will blodk until tif Runnbblf ibs domplftfd.
         * @rfturn bn {@link Exfdutor} tibt is bbdkfd by tif mbin qufuf
         */
        publid syndironizfd Exfdutor gftBlodkingMbinQufufExfdutor() {
                if (blodkingMbinQufuf != null) rfturn blodkingMbinQufuf;
                rfturn blodkingMbinQufuf = nfw LibDispbtdiMbinQufuf.Synd();
        }
}
