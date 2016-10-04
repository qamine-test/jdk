/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;
import jbvb.util.Mbp.Entry;

/**
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif <tt>Mbp</tt>
 * intfrfbdf, to minimizf tif fffort rfquirfd to implfmfnt tiis intfrfbdf.
 *
 * <p>To implfmfnt bn unmodifibblf mbp, tif progrbmmfr nffds only to fxtfnd tiis
 * dlbss bnd providf bn implfmfntbtion for tif <tt>fntrySft</tt> mftiod, wiidi
 * rfturns b sft-vifw of tif mbp's mbppings.  Typidblly, tif rfturnfd sft
 * will, in turn, bf implfmfntfd btop <tt>AbstrbdtSft</tt>.  Tiis sft siould
 * not support tif <tt>bdd</tt> or <tt>rfmovf</tt> mftiods, bnd its itfrbtor
 * siould not support tif <tt>rfmovf</tt> mftiod.
 *
 * <p>To implfmfnt b modifibblf mbp, tif progrbmmfr must bdditionblly ovfrridf
 * tiis dlbss's <tt>put</tt> mftiod (wiidi otifrwisf tirows bn
 * <tt>UnsupportfdOpfrbtionExdfption</tt>), bnd tif itfrbtor rfturnfd by
 * <tt>fntrySft().itfrbtor()</tt> must bdditionblly implfmfnt its
 * <tt>rfmovf</tt> mftiod.
 *
 * <p>Tif progrbmmfr siould gfnfrblly providf b void (no brgumfnt) bnd mbp
 * donstrudtor, bs pfr tif rfdommfndbtion in tif <tt>Mbp</tt> intfrfbdf
 * spfdifidbtion.
 *
 * <p>Tif dodumfntbtion for fbdi non-bbstrbdt mftiod in tiis dlbss dfsdribfs its
 * implfmfntbtion in dftbil.  Ebdi of tifsf mftiods mby bf ovfrriddfn if tif
 * mbp bfing implfmfntfd bdmits b morf fffidifnt implfmfntbtion.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Mbp
 * @sff Collfdtion
 * @sindf 1.2
 */

publid bbstrbdt dlbss AbstrbdtMbp<K,V> implfmfnts Mbp<K,V> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd AbstrbdtMbp() {
    }

    // Qufry Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns <tt>fntrySft().sizf()</tt>.
     */
    publid int sizf() {
        rfturn fntrySft().sizf();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns <tt>sizf() == 0</tt>.
     */
    publid boolfbn isEmpty() {
        rfturn sizf() == 0;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr <tt>fntrySft()</tt> sfbrdiing
     * for bn fntry witi tif spfdififd vbluf.  If sudi bn fntry is found,
     * <tt>truf</tt> is rfturnfd.  If tif itfrbtion tfrminbtfs witiout
     * finding sudi bn fntry, <tt>fblsf</tt> is rfturnfd.  Notf tibt tiis
     * implfmfntbtion rfquirfs linfbr timf in tif sizf of tif mbp.
     *
     * @tirows ClbssCbstExdfption   {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();
        if (vbluf==null) {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (f.gftVbluf()==null)
                    rfturn truf;
            }
        } flsf {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (vbluf.fqubls(f.gftVbluf()))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr <tt>fntrySft()</tt> sfbrdiing
     * for bn fntry witi tif spfdififd kfy.  If sudi bn fntry is found,
     * <tt>truf</tt> is rfturnfd.  If tif itfrbtion tfrminbtfs witiout
     * finding sudi bn fntry, <tt>fblsf</tt> is rfturnfd.  Notf tibt tiis
     * implfmfntbtion rfquirfs linfbr timf in tif sizf of tif mbp; mbny
     * implfmfntbtions will ovfrridf tiis mftiod.
     *
     * @tirows ClbssCbstExdfption   {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        Itfrbtor<Mbp.Entry<K,V>> i = fntrySft().itfrbtor();
        if (kfy==null) {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (f.gftKfy()==null)
                    rfturn truf;
            }
        } flsf {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (kfy.fqubls(f.gftKfy()))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr <tt>fntrySft()</tt> sfbrdiing
     * for bn fntry witi tif spfdififd kfy.  If sudi bn fntry is found,
     * tif fntry's vbluf is rfturnfd.  If tif itfrbtion tfrminbtfs witiout
     * finding sudi bn fntry, <tt>null</tt> is rfturnfd.  Notf tibt tiis
     * implfmfntbtion rfquirfs linfbr timf in tif sizf of tif mbp; mbny
     * implfmfntbtions will ovfrridf tiis mftiod.
     *
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     */
    publid V gft(Objfdt kfy) {
        Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();
        if (kfy==null) {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (f.gftKfy()==null)
                    rfturn f.gftVbluf();
            }
        } flsf {
            wiilf (i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (kfy.fqubls(f.gftKfy()))
                    rfturn f.gftVbluf();
            }
        }
        rfturn null;
    }


    // Modifidbtion Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion blwbys tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt>.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid V put(K kfy, V vbluf) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr <tt>fntrySft()</tt> sfbrdiing for bn
     * fntry witi tif spfdififd kfy.  If sudi bn fntry is found, its vbluf is
     * obtbinfd witi its <tt>gftVbluf</tt> opfrbtion, tif fntry is rfmovfd
     * from tif dollfdtion (bnd tif bbdking mbp) witi tif itfrbtor's
     * <tt>rfmovf</tt> opfrbtion, bnd tif sbvfd vbluf is rfturnfd.  If tif
     * itfrbtion tfrminbtfs witiout finding sudi bn fntry, <tt>null</tt> is
     * rfturnfd.  Notf tibt tiis implfmfntbtion rfquirfs linfbr timf in tif
     * sizf of tif mbp; mbny implfmfntbtions will ovfrridf tiis mftiod.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif <tt>fntrySft</tt>
     * itfrbtor dofs not support tif <tt>rfmovf</tt> mftiod bnd tiis mbp
     * dontbins b mbpping for tif spfdififd kfy.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     */
    publid V rfmovf(Objfdt kfy) {
        Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();
        Entry<K,V> dorrfdtEntry = null;
        if (kfy==null) {
            wiilf (dorrfdtEntry==null && i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (f.gftKfy()==null)
                    dorrfdtEntry = f;
            }
        } flsf {
            wiilf (dorrfdtEntry==null && i.ibsNfxt()) {
                Entry<K,V> f = i.nfxt();
                if (kfy.fqubls(f.gftKfy()))
                    dorrfdtEntry = f;
            }
        }

        V oldVbluf = null;
        if (dorrfdtEntry !=null) {
            oldVbluf = dorrfdtEntry.gftVbluf();
            i.rfmovf();
        }
        rfturn oldVbluf;
    }


    // Bulk Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tif spfdififd mbp's
     * <tt>fntrySft()</tt> dollfdtion, bnd dblls tiis mbp's <tt>put</tt>
     * opfrbtion ondf for fbdi fntry rfturnfd by tif itfrbtion.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tiis mbp dofs not support
     * tif <tt>put</tt> opfrbtion bnd tif spfdififd mbp is nonfmpty.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        for (Mbp.Entry<? fxtfnds K, ? fxtfnds V> f : m.fntrySft())
            put(f.gftKfy(), f.gftVbluf());
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion dblls <tt>fntrySft().dlfbr()</tt>.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif <tt>fntrySft</tt>
     * dofs not support tif <tt>dlfbr</tt> opfrbtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     */
    publid void dlfbr() {
        fntrySft().dlfbr();
    }


    // Vifws

    /**
     * Ebdi of tifsf fiflds brf initiblizfd to dontbin bn instbndf of tif
     * bppropribtf vifw tif first timf tiis vifw is rfqufstfd.  Tif vifws brf
     * stbtflfss, so tifrf's no rfbson to drfbtf morf tibn onf of fbdi.
     */
    trbnsifnt volbtilf Sft<K>        kfySft;
    trbnsifnt volbtilf Collfdtion<V> vblufs;

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns b sft tibt subdlbssfs {@link AbstrbdtSft}.
     * Tif subdlbss's itfrbtor mftiod rfturns b "wrbppfr objfdt" ovfr tiis
     * mbp's <tt>fntrySft()</tt> itfrbtor.  Tif <tt>sizf</tt> mftiod
     * dflfgbtfs to tiis mbp's <tt>sizf</tt> mftiod bnd tif
     * <tt>dontbins</tt> mftiod dflfgbtfs to tiis mbp's
     * <tt>dontbinsKfy</tt> mftiod.
     *
     * <p>Tif sft is drfbtfd tif first timf tiis mftiod is dbllfd,
     * bnd rfturnfd in rfsponsf to bll subsfqufnt dblls.  No syndironizbtion
     * is pfrformfd, so tifrf is b sligit dibndf tibt multiplf dblls to tiis
     * mftiod will not bll rfturn tif sbmf sft.
     */
    publid Sft<K> kfySft() {
        if (kfySft == null) {
            kfySft = nfw AbstrbdtSft<K>() {
                publid Itfrbtor<K> itfrbtor() {
                    rfturn nfw Itfrbtor<K>() {
                        privbtf Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();

                        publid boolfbn ibsNfxt() {
                            rfturn i.ibsNfxt();
                        }

                        publid K nfxt() {
                            rfturn i.nfxt().gftKfy();
                        }

                        publid void rfmovf() {
                            i.rfmovf();
                        }
                    };
                }

                publid int sizf() {
                    rfturn AbstrbdtMbp.tiis.sizf();
                }

                publid boolfbn isEmpty() {
                    rfturn AbstrbdtMbp.tiis.isEmpty();
                }

                publid void dlfbr() {
                    AbstrbdtMbp.tiis.dlfbr();
                }

                publid boolfbn dontbins(Objfdt k) {
                    rfturn AbstrbdtMbp.tiis.dontbinsKfy(k);
                }
            };
        }
        rfturn kfySft;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns b dollfdtion tibt subdlbssfs {@link
     * AbstrbdtCollfdtion}.  Tif subdlbss's itfrbtor mftiod rfturns b
     * "wrbppfr objfdt" ovfr tiis mbp's <tt>fntrySft()</tt> itfrbtor.
     * Tif <tt>sizf</tt> mftiod dflfgbtfs to tiis mbp's <tt>sizf</tt>
     * mftiod bnd tif <tt>dontbins</tt> mftiod dflfgbtfs to tiis mbp's
     * <tt>dontbinsVbluf</tt> mftiod.
     *
     * <p>Tif dollfdtion is drfbtfd tif first timf tiis mftiod is dbllfd, bnd
     * rfturnfd in rfsponsf to bll subsfqufnt dblls.  No syndironizbtion is
     * pfrformfd, so tifrf is b sligit dibndf tibt multiplf dblls to tiis
     * mftiod will not bll rfturn tif sbmf dollfdtion.
     */
    publid Collfdtion<V> vblufs() {
        if (vblufs == null) {
            vblufs = nfw AbstrbdtCollfdtion<V>() {
                publid Itfrbtor<V> itfrbtor() {
                    rfturn nfw Itfrbtor<V>() {
                        privbtf Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();

                        publid boolfbn ibsNfxt() {
                            rfturn i.ibsNfxt();
                        }

                        publid V nfxt() {
                            rfturn i.nfxt().gftVbluf();
                        }

                        publid void rfmovf() {
                            i.rfmovf();
                        }
                    };
                }

                publid int sizf() {
                    rfturn AbstrbdtMbp.tiis.sizf();
                }

                publid boolfbn isEmpty() {
                    rfturn AbstrbdtMbp.tiis.isEmpty();
                }

                publid void dlfbr() {
                    AbstrbdtMbp.tiis.dlfbr();
                }

                publid boolfbn dontbins(Objfdt v) {
                    rfturn AbstrbdtMbp.tiis.dontbinsVbluf(v);
                }
            };
        }
        rfturn vblufs;
    }

    publid bbstrbdt Sft<Entry<K,V>> fntrySft();


    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis mbp for fqublity.  Rfturns
     * <tt>truf</tt> if tif givfn objfdt is blso b mbp bnd tif two mbps
     * rfprfsfnt tif sbmf mbppings.  Morf formblly, two mbps <tt>m1</tt> bnd
     * <tt>m2</tt> rfprfsfnt tif sbmf mbppings if
     * <tt>m1.fntrySft().fqubls(m2.fntrySft())</tt>.  Tiis fnsurfs tibt tif
     * <tt>fqubls</tt> mftiod works propfrly bdross difffrfnt implfmfntbtions
     * of tif <tt>Mbp</tt> intfrfbdf.
     *
     * @implSpfd
     * Tiis implfmfntbtion first difdks if tif spfdififd objfdt is tiis mbp;
     * if so it rfturns <tt>truf</tt>.  Tifn, it difdks if tif spfdififd
     * objfdt is b mbp wiosf sizf is idfntidbl to tif sizf of tiis mbp; if
     * not, it rfturns <tt>fblsf</tt>.  If so, it itfrbtfs ovfr tiis mbp's
     * <tt>fntrySft</tt> dollfdtion, bnd difdks tibt tif spfdififd mbp
     * dontbins fbdi mbpping tibt tiis mbp dontbins.  If tif spfdififd mbp
     * fbils to dontbin sudi b mbpping, <tt>fblsf</tt> is rfturnfd.  If tif
     * itfrbtion domplftfs, <tt>truf</tt> is rfturnfd.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis mbp
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;

        if (!(o instbndfof Mbp))
            rfturn fblsf;
        Mbp<?,?> m = (Mbp<?,?>) o;
        if (m.sizf() != sizf())
            rfturn fblsf;

        try {
            for (Entry<K, V> f : fntrySft()) {
                K kfy = f.gftKfy();
                V vbluf = f.gftVbluf();
                if (vbluf == null) {
                    if (!(m.gft(kfy) == null && m.dontbinsKfy(kfy)))
                        rfturn fblsf;
                } flsf {
                    if (!vbluf.fqubls(m.gft(kfy)))
                        rfturn fblsf;
                }
            }
        } dbtdi (ClbssCbstExdfption unusfd) {
            rfturn fblsf;
        } dbtdi (NullPointfrExdfption unusfd) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis mbp.  Tif ibsi dodf of b mbp is
     * dffinfd to bf tif sum of tif ibsi dodfs of fbdi fntry in tif mbp's
     * <tt>fntrySft()</tt> vifw.  Tiis fnsurfs tibt <tt>m1.fqubls(m2)</tt>
     * implifs tibt <tt>m1.ibsiCodf()==m2.ibsiCodf()</tt> for bny two mbps
     * <tt>m1</tt> bnd <tt>m2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link Objfdt#ibsiCodf}.
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr <tt>fntrySft()</tt>, dblling
     * {@link Mbp.Entry#ibsiCodf ibsiCodf()} on fbdi flfmfnt (fntry) in tif
     * sft, bnd bdding up tif rfsults.
     *
     * @rfturn tif ibsi dodf vbluf for tiis mbp
     * @sff Mbp.Entry#ibsiCodf()
     * @sff Objfdt#fqubls(Objfdt)
     * @sff Sft#fqubls(Objfdt)
     */
    publid int ibsiCodf() {
        int i = 0;
        for (Entry<K, V> fntry : fntrySft())
            i += fntry.ibsiCodf();
        rfturn i;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis mbp.  Tif string rfprfsfntbtion
     * donsists of b list of kfy-vbluf mbppings in tif ordfr rfturnfd by tif
     * mbp's <tt>fntrySft</tt> vifw's itfrbtor, fndlosfd in brbdfs
     * (<tt>"{}"</tt>).  Adjbdfnt mbppings brf sfpbrbtfd by tif dibrbdtfrs
     * <tt>", "</tt> (dommb bnd spbdf).  Ebdi kfy-vbluf mbpping is rfndfrfd bs
     * tif kfy followfd by bn fqubls sign (<tt>"="</tt>) followfd by tif
     * bssodibtfd vbluf.  Kfys bnd vblufs brf donvfrtfd to strings bs by
     * {@link String#vblufOf(Objfdt)}.
     *
     * @rfturn b string rfprfsfntbtion of tiis mbp
     */
    publid String toString() {
        Itfrbtor<Entry<K,V>> i = fntrySft().itfrbtor();
        if (! i.ibsNfxt())
            rfturn "{}";

        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('{');
        for (;;) {
            Entry<K,V> f = i.nfxt();
            K kfy = f.gftKfy();
            V vbluf = f.gftVbluf();
            sb.bppfnd(kfy   == tiis ? "(tiis Mbp)" : kfy);
            sb.bppfnd('=');
            sb.bppfnd(vbluf == tiis ? "(tiis Mbp)" : vbluf);
            if (! i.ibsNfxt())
                rfturn sb.bppfnd('}').toString();
            sb.bppfnd(',').bppfnd(' ');
        }
    }

    /**
     * Rfturns b sibllow dopy of tiis <tt>AbstrbdtMbp</tt> instbndf: tif kfys
     * bnd vblufs tifmsflvfs brf not dlonfd.
     *
     * @rfturn b sibllow dopy of tiis mbp
     */
    protfdtfd Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        AbstrbdtMbp<?,?> rfsult = (AbstrbdtMbp<?,?>)supfr.dlonf();
        rfsult.kfySft = null;
        rfsult.vblufs = null;
        rfturn rfsult;
    }

    /**
     * Utility mftiod for SimplfEntry bnd SimplfImmutbblfEntry.
     * Tfst for fqublity, difdking for nulls.
     *
     * NB: Do not rfplbdf witi Objfdt.fqubls until JDK-8015417 is rfsolvfd.
     */
    privbtf stbtid boolfbn fq(Objfdt o1, Objfdt o2) {
        rfturn o1 == null ? o2 == null : o1.fqubls(o2);
    }

    // Implfmfntbtion Notf: SimplfEntry bnd SimplfImmutbblfEntry
    // brf distindt unrflbtfd dlbssfs, fvfn tiougi tify sibrf
    // somf dodf. Sindf you dbn't bdd or subtrbdt finbl-nfss
    // of b fifld in b subdlbss, tify dbn't sibrf rfprfsfntbtions,
    // bnd tif bmount of duplidbtfd dodf is too smbll to wbrrbnt
    // fxposing b dommon bbstrbdt dlbss.


    /**
     * An Entry mbintbining b kfy bnd b vbluf.  Tif vbluf mby bf
     * dibngfd using tif <tt>sftVbluf</tt> mftiod.  Tiis dlbss
     * fbdilitbtfs tif prodfss of building dustom mbp
     * implfmfntbtions. For fxbmplf, it mby bf donvfnifnt to rfturn
     * brrbys of <tt>SimplfEntry</tt> instbndfs in mftiod
     * <tt>Mbp.fntrySft().toArrby</tt>.
     *
     * @sindf 1.6
     */
    publid stbtid dlbss SimplfEntry<K,V>
        implfmfnts Entry<K,V>, jbvb.io.Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -8499721149061103585L;

        privbtf finbl K kfy;
        privbtf V vbluf;

        /**
         * Crfbtfs bn fntry rfprfsfnting b mbpping from tif spfdififd
         * kfy to tif spfdififd vbluf.
         *
         * @pbrbm kfy tif kfy rfprfsfntfd by tiis fntry
         * @pbrbm vbluf tif vbluf rfprfsfntfd by tiis fntry
         */
        publid SimplfEntry(K kfy, V vbluf) {
            tiis.kfy   = kfy;
            tiis.vbluf = vbluf;
        }

        /**
         * Crfbtfs bn fntry rfprfsfnting tif sbmf mbpping bs tif
         * spfdififd fntry.
         *
         * @pbrbm fntry tif fntry to dopy
         */
        publid SimplfEntry(Entry<? fxtfnds K, ? fxtfnds V> fntry) {
            tiis.kfy   = fntry.gftKfy();
            tiis.vbluf = fntry.gftVbluf();
        }

        /**
         * Rfturns tif kfy dorrfsponding to tiis fntry.
         *
         * @rfturn tif kfy dorrfsponding to tiis fntry
         */
        publid K gftKfy() {
            rfturn kfy;
        }

        /**
         * Rfturns tif vbluf dorrfsponding to tiis fntry.
         *
         * @rfturn tif vbluf dorrfsponding to tiis fntry
         */
        publid V gftVbluf() {
            rfturn vbluf;
        }

        /**
         * Rfplbdfs tif vbluf dorrfsponding to tiis fntry witi tif spfdififd
         * vbluf.
         *
         * @pbrbm vbluf nfw vbluf to bf storfd in tiis fntry
         * @rfturn tif old vbluf dorrfsponding to tif fntry
         */
        publid V sftVbluf(V vbluf) {
            V oldVbluf = tiis.vbluf;
            tiis.vbluf = vbluf;
            rfturn oldVbluf;
        }

        /**
         * Compbrfs tif spfdififd objfdt witi tiis fntry for fqublity.
         * Rfturns {@dodf truf} if tif givfn objfdt is blso b mbp fntry bnd
         * tif two fntrifs rfprfsfnt tif sbmf mbpping.  Morf formblly, two
         * fntrifs {@dodf f1} bnd {@dodf f2} rfprfsfnt tif sbmf mbpping
         * if<prf>
         *   (f1.gftKfy()==null ?
         *    f2.gftKfy()==null :
         *    f1.gftKfy().fqubls(f2.gftKfy()))
         *   &bmp;&bmp;
         *   (f1.gftVbluf()==null ?
         *    f2.gftVbluf()==null :
         *    f1.gftVbluf().fqubls(f2.gftVbluf()))</prf>
         * Tiis fnsurfs tibt tif {@dodf fqubls} mftiod works propfrly bdross
         * difffrfnt implfmfntbtions of tif {@dodf Mbp.Entry} intfrfbdf.
         *
         * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp fntry
         * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis mbp
         *         fntry
         * @sff    #ibsiCodf
         */
        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            rfturn fq(kfy, f.gftKfy()) && fq(vbluf, f.gftVbluf());
        }

        /**
         * Rfturns tif ibsi dodf vbluf for tiis mbp fntry.  Tif ibsi dodf
         * of b mbp fntry {@dodf f} is dffinfd to bf: <prf>
         *   (f.gftKfy()==null   ? 0 : f.gftKfy().ibsiCodf()) ^
         *   (f.gftVbluf()==null ? 0 : f.gftVbluf().ibsiCodf())</prf>
         * Tiis fnsurfs tibt {@dodf f1.fqubls(f2)} implifs tibt
         * {@dodf f1.ibsiCodf()==f2.ibsiCodf()} for bny two Entrifs
         * {@dodf f1} bnd {@dodf f2}, bs rfquirfd by tif gfnfrbl
         * dontrbdt of {@link Objfdt#ibsiCodf}.
         *
         * @rfturn tif ibsi dodf vbluf for tiis mbp fntry
         * @sff    #fqubls
         */
        publid int ibsiCodf() {
            rfturn (kfy   == null ? 0 :   kfy.ibsiCodf()) ^
                   (vbluf == null ? 0 : vbluf.ibsiCodf());
        }

        /**
         * Rfturns b String rfprfsfntbtion of tiis mbp fntry.  Tiis
         * implfmfntbtion rfturns tif string rfprfsfntbtion of tiis
         * fntry's kfy followfd by tif fqubls dibrbdtfr ("<tt>=</tt>")
         * followfd by tif string rfprfsfntbtion of tiis fntry's vbluf.
         *
         * @rfturn b String rfprfsfntbtion of tiis mbp fntry
         */
        publid String toString() {
            rfturn kfy + "=" + vbluf;
        }

    }

    /**
     * An Entry mbintbining bn immutbblf kfy bnd vbluf.  Tiis dlbss
     * dofs not support mftiod <tt>sftVbluf</tt>.  Tiis dlbss mby bf
     * donvfnifnt in mftiods tibt rfturn tirfbd-sbff snbpsiots of
     * kfy-vbluf mbppings.
     *
     * @sindf 1.6
     */
    publid stbtid dlbss SimplfImmutbblfEntry<K,V>
        implfmfnts Entry<K,V>, jbvb.io.Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 7138329143949025153L;

        privbtf finbl K kfy;
        privbtf finbl V vbluf;

        /**
         * Crfbtfs bn fntry rfprfsfnting b mbpping from tif spfdififd
         * kfy to tif spfdififd vbluf.
         *
         * @pbrbm kfy tif kfy rfprfsfntfd by tiis fntry
         * @pbrbm vbluf tif vbluf rfprfsfntfd by tiis fntry
         */
        publid SimplfImmutbblfEntry(K kfy, V vbluf) {
            tiis.kfy   = kfy;
            tiis.vbluf = vbluf;
        }

        /**
         * Crfbtfs bn fntry rfprfsfnting tif sbmf mbpping bs tif
         * spfdififd fntry.
         *
         * @pbrbm fntry tif fntry to dopy
         */
        publid SimplfImmutbblfEntry(Entry<? fxtfnds K, ? fxtfnds V> fntry) {
            tiis.kfy   = fntry.gftKfy();
            tiis.vbluf = fntry.gftVbluf();
        }

        /**
         * Rfturns tif kfy dorrfsponding to tiis fntry.
         *
         * @rfturn tif kfy dorrfsponding to tiis fntry
         */
        publid K gftKfy() {
            rfturn kfy;
        }

        /**
         * Rfturns tif vbluf dorrfsponding to tiis fntry.
         *
         * @rfturn tif vbluf dorrfsponding to tiis fntry
         */
        publid V gftVbluf() {
            rfturn vbluf;
        }

        /**
         * Rfplbdfs tif vbluf dorrfsponding to tiis fntry witi tif spfdififd
         * vbluf (optionbl opfrbtion).  Tiis implfmfntbtion simply tirows
         * <tt>UnsupportfdOpfrbtionExdfption</tt>, bs tiis dlbss implfmfnts
         * bn <i>immutbblf</i> mbp fntry.
         *
         * @pbrbm vbluf nfw vbluf to bf storfd in tiis fntry
         * @rfturn (Dofs not rfturn)
         * @tirows UnsupportfdOpfrbtionExdfption blwbys
         */
        publid V sftVbluf(V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Compbrfs tif spfdififd objfdt witi tiis fntry for fqublity.
         * Rfturns {@dodf truf} if tif givfn objfdt is blso b mbp fntry bnd
         * tif two fntrifs rfprfsfnt tif sbmf mbpping.  Morf formblly, two
         * fntrifs {@dodf f1} bnd {@dodf f2} rfprfsfnt tif sbmf mbpping
         * if<prf>
         *   (f1.gftKfy()==null ?
         *    f2.gftKfy()==null :
         *    f1.gftKfy().fqubls(f2.gftKfy()))
         *   &bmp;&bmp;
         *   (f1.gftVbluf()==null ?
         *    f2.gftVbluf()==null :
         *    f1.gftVbluf().fqubls(f2.gftVbluf()))</prf>
         * Tiis fnsurfs tibt tif {@dodf fqubls} mftiod works propfrly bdross
         * difffrfnt implfmfntbtions of tif {@dodf Mbp.Entry} intfrfbdf.
         *
         * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp fntry
         * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis mbp
         *         fntry
         * @sff    #ibsiCodf
         */
        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            rfturn fq(kfy, f.gftKfy()) && fq(vbluf, f.gftVbluf());
        }

        /**
         * Rfturns tif ibsi dodf vbluf for tiis mbp fntry.  Tif ibsi dodf
         * of b mbp fntry {@dodf f} is dffinfd to bf: <prf>
         *   (f.gftKfy()==null   ? 0 : f.gftKfy().ibsiCodf()) ^
         *   (f.gftVbluf()==null ? 0 : f.gftVbluf().ibsiCodf())</prf>
         * Tiis fnsurfs tibt {@dodf f1.fqubls(f2)} implifs tibt
         * {@dodf f1.ibsiCodf()==f2.ibsiCodf()} for bny two Entrifs
         * {@dodf f1} bnd {@dodf f2}, bs rfquirfd by tif gfnfrbl
         * dontrbdt of {@link Objfdt#ibsiCodf}.
         *
         * @rfturn tif ibsi dodf vbluf for tiis mbp fntry
         * @sff    #fqubls
         */
        publid int ibsiCodf() {
            rfturn (kfy   == null ? 0 :   kfy.ibsiCodf()) ^
                   (vbluf == null ? 0 : vbluf.ibsiCodf());
        }

        /**
         * Rfturns b String rfprfsfntbtion of tiis mbp fntry.  Tiis
         * implfmfntbtion rfturns tif string rfprfsfntbtion of tiis
         * fntry's kfy followfd by tif fqubls dibrbdtfr ("<tt>=</tt>")
         * followfd by tif string rfprfsfntbtion of tiis fntry's vbluf.
         *
         * @rfturn b String rfprfsfntbtion of tiis mbp fntry
         */
        publid String toString() {
            rfturn kfy + "=" + vbluf;
        }

    }

}
