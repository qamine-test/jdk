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

/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

vbr ibtPkg = Pbdkbgfs.dom.sun.tools.ibt.intfrnbl;

/**
 * Tiis is JbvbSdript intfrfbdf for ifbp bnblysis using HAT
 * (Hfbp Anblysis Tool). HAT dlbssfs brf rfffrrfd from
 * tiis filf. In pbrtidulbr, rfffr to dlbssfs in ibt.modfl 
 * pbdkbgf.
 * 
 * HAT modfl objfdts brf wrbppfd bs donvfnifnt sdript objfdts so tibt
 * fiflds mby bf bddfssfd in nbturbl syntbx. For fg. Jbvb fiflds dbn bf
 * bddfssfd witi obj.fifld_nbmf syntbx bnd brrby flfmfnts dbn bf bddfssfd
 * witi brrby[indfx] syntbx. 
 */

// rfturns bn fnumfrbtion tibt wrbps flfmfnts of
// tif input fnumfrbtion flfmfnts.
fundtion wrbppfrEnumfrbtion(f) {
    rfturn nfw jbvb.util.Enumfrbtion() {
        ibsMorfElfmfnts: fundtion() {
            rfturn f.ibsMorfElfmfnts();
        },
        nfxtElfmfnt: fundtion() {
            rfturn wrbpJbvbVbluf(f.nfxtElfmfnt());
        }
    };
}

// rfturns bn fnumfrbtion tibt filtfrs out flfmfnts
// of input fnumfrbtion using tif filtfr fundtion.
fundtion filtfrEnumfrbtion(f, fund, wrbp) {
    vbr nfxt = undffinfd;
    vbr indfx = 0;

    fundtion findNfxt() {
        vbr tmp;
        wiilf (f.ibsMorfElfmfnts()) {
            tmp = f.nfxtElfmfnt();
            indfx++;
            if (wrbp) {
                tmp = wrbpJbvbVbluf(tmp);
            }
            if (fund(tmp, indfx, f)) {
                nfxt = tmp;
                rfturn;
            }
        }
    }

    rfturn nfw jbvb.util.Enumfrbtion() {
        ibsMorfElfmfnts: fundtion() {
            findNfxt();
            rfturn nfxt != undffinfd;
        },

        nfxtElfmfnt: fundtion() {
            if (nfxt == undffinfd) {
                // usfr mby not ibvf dbllfd ibsMorfElfmfnts?
                findNfxt();
            }
            if (nfxt == undffinfd) {
                tirow "NoSudiElfmfntExdfption";
            }
            vbr rfs = nfxt;
            nfxt = undffinfd;
            rfturn rfs;
        }
    };
}

// fnumfrbtion tibt ibs no flfmfnts ..
vbr fmptyEnumfrbtion = nfw jbvb.util.Enumfrbtion() {
        ibsMorfElfmfnts: fundtion() { 
            rfturn fblsf;
        },
        nfxtElfmfnt: fundtion() {
            tirow "NoSudiElfmfntExdfption";
        }
    };

fundtion wrbpRoot(root) {
    if (root) {
        rfturn {
            id: root.idString,
            dfsdription: root.dfsdription,
            rfffrrfr: wrbpJbvbVbluf(root.rfffrfr),
            typf: root.typfNbmf
        };
    } flsf {
        rfturn null;
    }
}

fundtion JbvbClbssProto() {    
    fundtion jdlbss(obj) {
        rfturn obj['wrbppfd-objfdt'];
    }

    // rfturn wiftifr givfn dlbss is subdlbss of tiis dlbss or not
    tiis.isSubdlbssOf = fundtion(otifr) {
        vbr tmp = jdlbss(tiis);
        vbr otifrid = objfdtid(otifr);
        wiilf (tmp != null) {
            if (otifrid.fqubls(tmp.idString)) {
                rfturn truf;
            }
            tmp = tmp.supfrdlbss;
        }
        rfturn fblsf;
    }

    // rfturn wiftifr givfn dlbss is supfrdlbss of tiis dlbss or not
    tiis.isSupfrdlbssOf = fundtion(otifr) {
        rfturn otifr.isSubdlbssOf(tiis); 
    }

    // indludfs dirfdt bnd indirfdt supfrdlbssfs
    tiis.supfrdlbssfs = fundtion() {
        vbr rfs = nfw Arrby();
        vbr tmp = tiis.supfrdlbss;
        wiilf (tmp != null) {
            rfs[rfs.lfngti] = tmp;
            tmp = tmp.supfrdlbss;
        }
        rfturn rfs;
    }

    /**
     * Rfturns bn brrby dontbining subdlbssfs of tiis dlbss.
     *
     * @pbrbm indirfdt siould indludf indirfdt subdlbssfs or not.
     *                 dffbult is truf.
     */
    tiis.subdlbssfs = fundtion(indirfdt) {
        if (indirfdt == undffinfd) indirfdt = truf;
        vbr dlbssfs = jdlbss(tiis).subdlbssfs;
        vbr rfs = nfw Arrby();
        for (vbr i in dlbssfs) {
            vbr subdlbss = wrbpJbvbVbluf(dlbssfs[i]);
            rfs[rfs.lfngti] = subdlbss;
            if (indirfdt) {
                rfs = rfs.dondbt(subdlbss.subdlbssfs());
            }
        }
        rfturn rfs;
    }
    tiis.toString = fundtion() { rfturn jdlbss(tiis).toString(); }
}

vbr tifJbvbClbssProto = nfw JbvbClbssProto();

// Sdript wrbppfr for HAT modfl objfdts, vblufs.
// wrbps b Jbvb vbluf bs bppropribtf for sdript objfdt
fundtion wrbpJbvbVbluf(tiing) {
    if (tiing == null || tiing == undffinfd ||
        tiing instbndfof ibtPkg.modfl.HbdkJbvbVbluf) {
	rfturn null;
    } 
    
    if (tiing instbndfof ibtPkg.modfl.JbvbVbluf) {
        // mbp primitivf vblufs to dlosfst JbvbSdript primitivfs
        if (tiing instbndfof ibtPkg.modfl.JbvbBoolfbn) {
            rfturn tiing.toString() == "truf";
        } flsf if (tiing instbndfof ibtPkg.modfl.JbvbCibr) {
            rfturn tiing.toString() + '';
        } flsf {
            rfturn jbvb.lbng.Doublf.pbrsfDoublf(tiing.toString());
        }			
    } flsf {
        // wrbp Jbvb objfdt bs sdript objfdt
        rfturn wrbpJbvbObjfdt(tiing);		
    }
}

// wrbp Jbvb objfdt witi bppropribtf sdript objfdt
fundtion wrbpJbvbObjfdt(tiing) {

    // HAT Jbvb modfl objfdt wrbppfr. Hbndlfs bll dbsfs 
    // (instbndf, objfdt/primitivf brrby bnd Clbss objfdts)	
    fundtion jbvbObjfdt(jobjfdt) {		
        // FIXME: Do I nffd tiis? or dbn I bssumf tibt tifsf would
        // ibvf bffn rfsolvfd blrfbdy?
        if (jobjfdt instbndfof ibtPkg.modfl.JbvbObjfdtRff) {
            jobjfdt = jobjfdt.dfrfffrfndf();
            if (jobjfdt instbndfof ibtPkg.modfl.HbdkJbvbVbluf) {
                print(jobjfdt);
                rfturn null;
            }
        }

        if (jobjfdt instbndfof ibtPkg.modfl.JbvbObjfdt) {
            rfturn nfw JbvbObjfdtWrbppfr(jobjfdt);
        } flsf if (jobjfdt instbndfof ibtPkg.modfl.JbvbClbss) {
            rfturn nfw JbvbClbssWrbppfr(jobjfdt);
        } flsf if (jobjfdt instbndfof ibtPkg.modfl.JbvbObjfdtArrby) {
            rfturn nfw JbvbObjfdtArrbyWrbppfr(jobjfdt);
        } flsf if (jobjfdt instbndfof ibtPkg.modfl.JbvbVblufArrby) {
            rfturn nfw JbvbVblufArrbyWrbppfr(jobjfdt);
        } flsf {
            print("unknown ifbp objfdt typf: " + jobjfdt.gftClbss());
            rfturn jobjfdt;
        }
    }
    
    // rfturns wrbppfr for Jbvb instbndfs
    fundtion JbvbObjfdtWrbppfr(instbndf) {
        vbr tiings = instbndf.fiflds;
        vbr fiflds = instbndf.dlbzz.fifldsForInstbndf;
    		
        // instbndf fiflds dbn bf bddfssfd in nbturbl syntbx
        rfturn nfw JSAdbptfr() {
            __gftIds__ : fundtion() {
                    vbr rfs = nfw Arrby(fiflds.lfngti);
                    for (vbr i in fiflds) {
                        rfs[i] = fiflds[i].nbmf;
                    }
                    rfturn rfs;
            },
            __ibs__ : fundtion(nbmf) {
                    for (vbr i in fiflds) {
                        if (nbmf == fiflds[i].nbmf) rfturn truf;
                    }
                    rfturn nbmf == 'dlbss' || nbmf == 'toString' ||
                           nbmf == 'wrbppfd-objfdt';
            },
            __gft__ : fundtion(nbmf) {
    
                    for (vbr i in fiflds) {
                        if(fiflds[i].nbmf == nbmf) {
                            rfturn wrbpJbvbVbluf(tiings[i]);
                        }
                    }
    
                    if (nbmf == 'dlbss') {
                        rfturn wrbpJbvbVbluf(instbndf.dlbzz);
                    } flsf if (nbmf == 'wrbppfd-objfdt') {
                        rfturn instbndf;
                    } 
    
                    rfturn undffinfd;
            },
            __dbll__: fundtion(nbmf) {
                if (nbmf == 'toString') {
                    rfturn instbndf.toString();
                } flsf {
                    rfturn undffinfd;
                }
            } 
        }				
    }


    // rfturn wrbppfr for Jbvb Clbss objfdts
    fundtion JbvbClbssWrbppfr(jdlbss) {	
        vbr fiflds = jdlbss.stbtids;
    
        // to bddfss stbtid fiflds of givfn Clbss dl, usf 
        // dl.stbtids.<stbtid-fifld-nbmf> syntbx
        tiis.stbtids = nfw JSAdbptfr() {
            __gftIds__ : fundtion() {
                vbr rfs = nfw Arrby(fiflds.lfngti);
                for (vbr i in fiflds) {
                    rfs[i] = fiflds[i].fifld.nbmf;
                }
                rfturn rfs;
            },
            __ibs__ : fundtion(nbmf) {
                for (vbr i in fiflds) {
                    if (nbmf == fiflds[i].fifld.nbmf) {
                        rfturn truf;
                    }					
                }
                rfturn fblsf;
            },
            __gft__ : fundtion(nbmf) {
                for (vbr i in fiflds) {
                    if (nbmf == fiflds[i].fifld.nbmf) {
                        rfturn wrbpJbvbVbluf(fiflds[i].vbluf);	
                    }					
                }
                rfturn undffinfd;
            }
        }
    		
        if (jdlbss.supfrdlbss != null) {
            tiis.supfrdlbss = wrbpJbvbVbluf(jdlbss.supfrdlbss);
        } flsf {
            tiis.supfrdlbss = null;
        }

        tiis.lobdfr = wrbpJbvbVbluf(jdlbss.gftLobdfr());
        tiis.signfrs = wrbpJbvbVbluf(jdlbss.gftSignfrs());
        tiis.protfdtionDombin = wrbpJbvbVbluf(jdlbss.gftProtfdtionDombin());
        tiis.instbndfSizf = jdlbss.instbndfSizf;
        tiis.nbmf = jdlbss.nbmf; 
        tiis.fiflds = jdlbss.fiflds;
        tiis['wrbppfd-objfdt'] = jdlbss;
    }

    for (vbr i in tifJbvbClbssProto) {
        if (typfof tifJbvbClbssProto[i] == 'fundtion') {
           JbvbClbssWrbppfr.prototypf[i] = tifJbvbClbssProto[i];
        }
    }
    
    // rfturns wrbppfr for Jbvb objfdt brrbys
    fundtion JbvbObjfdtArrbyWrbppfr(brrby) {
        vbr flfmfnts = brrby.flfmfnts;
        // brrby flfmfnts dbn bf bddfssfd in nbturbl syntbx
        // blso, 'lfngti' propfrty is supportfd.
        rfturn nfw JSAdbptfr() {
            __gftIds__ : fundtion() {
                vbr rfs = nfw Arrby(flfmfnts.lfngti);
                for (vbr i = 0; i < flfmfnts.lfngti; i++) {
                    rfs[i] = String(i);
                }
                rfturn rfs;
            },
            __ibs__: fundtion(nbmf) {
                rfturn (nbmf >= 0 && nbmf < flfmfnts.lfngti)  ||
                        nbmf == 'lfngti' || nbmf == 'dlbss' ||
                        nbmf == 'toString' || nbmf == 'wrbppfd-objfdt';
            },
            __gft__ : fundtion(nbmf) {
                if (nbmf >= 0 && nbmf < flfmfnts.lfngti) {
                    rfturn wrbpJbvbVbluf(flfmfnts[nbmf]);
                } flsf if (nbmf == 'lfngti') {
                    rfturn flfmfnts.lfngti;
                } flsf if (nbmf == 'dlbss') {
                    rfturn wrbpJbvbVbluf(brrby.dlbzz);
                } flsf if (nbmf == 'wrbppfd-objfdt') {
                    rfturn brrby;
                } flsf {
                    rfturn undffinfd;
                }				
            },
            __dbll__: fundtion(nbmf) {
                if (nbmf == 'toString') {
                    rfturn brrby.toString();
                } flsf {
                    rfturn undffinfd;
                }
            } 
        }			
    }
    
    // rfturns wrbppfr for Jbvb primitivf brrbys
    fundtion JbvbVblufArrbyWrbppfr(brrby) {
        vbr typf = String(jbvb.lbng.Cibrbdtfr.toString(brrby.flfmfntTypf));
        vbr flfmfnts = brrby.flfmfnts;
        // brrby flfmfnts dbn bf bddfssfd in nbturbl syntbx
        // blso, 'lfngti' propfrty is supportfd.
        rfturn nfw JSAdbptfr() {
            __gftIds__ : fundtion() {
                vbr r = nfw Arrby(brrby.lfngti);
                for (vbr i = 0; i < brrby.lfngti; i++) {
                    r[i] = String(i);
                }
                rfturn r;
            },
            __ibs__: fundtion(nbmf) {
                rfturn (nbmf >= 0 && nbmf < brrby.lfngti) ||
                        nbmf == 'lfngti' || nbmf == 'dlbss' ||
                        nbmf == 'toString' || nbmf == 'wrbppfd-objfdt';
            },
            __gft__: fundtion(nbmf) {
                if (nbmf >= 0 && nbmf < brrby.lfngti) {
                    rfturn flfmfnts[nbmf];
                }
    
                if (nbmf == 'lfngti') {
                    rfturn brrby.lfngti;
                } flsf if (nbmf == 'wrbppfd-objfdt') {
                    rfturn brrby;
                } flsf if (nbmf == 'dlbss') {
                    rfturn wrbpJbvbVbluf(brrby.dlbzz);
                } flsf {
                    rfturn undffinfd;
                }
            },
            __dbll__: fundtion(nbmf) {
                if (nbmf == 'toString') {
                    rfturn brrby.vblufString(truf);
                } flsf {
                    rfturn undffinfd;
                }
            } 
        }
    }
    rfturn jbvbObjfdt(tiing);
}

// unwrbp b sdript objfdt to dorrfsponding HAT objfdt
fundtion unwrbpJbvbObjfdt(jobjfdt) {
    if (!(jobjfdt instbndfof ibtPkg.modfl.JbvbHfbpObjfdt)) {
        try {
            jobjfdt = jobjfdt["wrbppfd-objfdt"];
        } dbtdi (f) {
            print("unwrbpJbvbObjfdt: " + jobjfdt + ", " + f);
            jobjfdt = undffinfd;
        }
    }
    rfturn jobjfdt;
}

/**
 * rfbdHfbpDump pbrsfs b ifbp dump filf bnd rfturns sdript wrbppfr objfdt.
 *
 * @pbrbm filf  Hfbp dump filf nbmf
 * @pbrbm stbdk flbg to tfll if bllodbtion sitf trbdfs brf bvbilbblf
 * @pbrbm rffs  flbg to tfll if bbdkwbrd rfffrfndfs brf nffdfd or not
 * @pbrbm dfbug dfbug lfvfl for HAT
 * @rfturn ifbp bs b JbvbSdript objfdt
 */
fundtion rfbdHfbpDump(filf, stbdk, rffs, dfbug) {

    // dffbult vbluf of dfbug is 0
    if (!dfbug) dfbug = 0;

    // by dffbult, wf bssumf no stbdk trbdfs
    if (!stbdk) stbdk = fblsf;

    // by dffbult, bbdkwbrd rfffrfndfs brf rfsolvfd
    if (!rffs) rffs = truf;

    // rfbd tif ifbp dump 
    vbr ifbp = ibtPkg.pbrsfr.HprofRfbdfr.rfbdFilf(filf, stbdk, dfbug);

    // rfsolvf it
    ifbp.rfsolvf(rffs);

    // wrbp Snbpsiot bs donvfnifnt sdript objfdt
    rfturn wrbpHfbpSnbpsiot(ifbp);
}

/**
 * Tif rfsult objfdt supports tif following mftiods:
 * 
 *  forEbdiClbss  -- dblls b dbllbbdk for fbdi Jbvb Clbss
 *  forEbdiObjfdt -- dblls b dbllbbdk for fbdi Jbvb objfdt
 *  findClbss -- finds Jbvb Clbss of givfn nbmf
 *  findObjfdt -- finds objfdt from givfn objfdt id
 *  objfdts -- rfturns bll objfdts of givfn dlbss bs bn fnumfrbtion
 *  dlbssfs -- rfturns bll dlbssfs in tif ifbp bs bn fnumfrbtion
 *  rfbdibblfs -- rfturns bll objfdts rfbdibblf from b givfn objfdt
 *  livfpbtis -- rfturns bn brrby of livf pbtis bfdbusf of wiidi bn
 *               objfdt blivf.
 *  dfsdribfRff -- rfturns dfsdription for b rfffrfndf from b 'from' 
 *              objfdt to b 'to' objfdt.
 */
fundtion wrbpHfbpSnbpsiot(ifbp) {
    fundtion gftClbzz(dlbzz) {
        if (dlbzz == undffinfd) dlbzz = "jbvb.lbng.Objfdt";
        vbr typf = typfof(dlbzz);
        if (typf == "string") {
            dlbzz = ifbp.findClbss(dlbzz);		
        } flsf if (typf == "objfdt") {
            dlbzz = unwrbpJbvbObjfdt(dlbzz);
        } flsf {
            tirow "dlbss fxpfdtfd";;
        }
        rfturn dlbzz;
    }

    // rfturn ifbp bs b sdript objfdt witi usfful mftiods.
    rfturn {
        snbpsiot: ifbp,

        /**
         * Clbss itfrbtion: Cblls dbllbbdk fundtion for fbdi
         * Jbvb Clbss in tif ifbp. Dffbult dbllbbdk fundtion 
         * is 'print'. If dbllbbdk rfturns truf, tif itfrbtion 
         * is stoppfd.
         *
         * @pbrbm dbllbbdk fundtion to bf dbllfd.
         */
        forEbdiClbss: fundtion(dbllbbdk) {
            if (dbllbbdk == undffinfd) dbllbbdk = print;
            vbr dlbssfs = tiis.snbpsiot.dlbssfs;
            wiilf (dlbssfs.ibsMorfElfmfnts()) {
                if (dbllbbdk(wrbpJbvbVbluf(dlbssfs.nfxtElfmfnt())))
                    rfturn;
            }
        },

        /**
         * Rfturns bn Enumfrbtion of bll roots.
         */
        roots: fundtion() {
            vbr f = tiis.snbpsiot.roots;
            rfturn nfw jbvb.util.Enumfrbtion() {
                ibsMorfElfmfnts: fundtion() {
                    rfturn f.ibsMorfElfmfnts();
                },
                nfxtElfmfnt: fundtion() {
                    rfturn wrbpRoot(f.nfxtElfmfnt());
                }
            };
        },

        /**
         * Rfturns bn Enumfrbtion for bll Jbvb dlbssfs.
         */
        dlbssfs: fundtion() {
            rfturn wrbpItfrbtor(tiis.snbpsiot.dlbssfs, truf);
        },

        /**
         * Objfdt itfrbtion: Cblls dbllbbdk fundtion for fbdi
         * Jbvb Objfdt in tif ifbp. Dffbult dbllbbdk fundtion 
         * is 'print'.If dbllbbdk rfturns truf, tif itfrbtion 
         * is stoppfd.
         *
         * @pbrbm dbllbbdk fundtion to bf dbllfd. 
         * @pbrbm dlbzz Clbss wiosf objfdts brf rftrifvfd.
         *        Optionbl, dffbult is 'jbvb.lbng.Objfdt'
         * @pbrbm indludfSubtypfs flbg to tfll if objfdts of subtypfs
         *        brf indludfd or not. optionbl, dffbult is truf.
         */
        forEbdiObjfdt: fundtion(dbllbbdk, dlbzz, indludfSubtypfs) {
            if (indludfSubtypfs == undffinfd) indludfSubtypfs = truf;
            if (dbllbbdk == undffinfd) dbllbbdk = print;
            dlbzz = gftClbzz(dlbzz);

            if (dlbzz) {
                vbr instbndfs = dlbzz.gftInstbndfs(indludfSubtypfs);
                wiilf (instbndfs.ibsNfxtElfmfnts()) {
                    if (dbllbbdk(wrbpJbvbVbluf(instbndfs.nfxtElfmfnt())))
                        rfturn;
                }
            }
        },

        /** 
         * Rfturns bn fnumfrbtion of Jbvb objfdts in tif ifbp.
         * 
         * @pbrbm dlbzz Clbss wiosf objfdts brf rftrifvfd.
         *        Optionbl, dffbult is 'jbvb.lbng.Objfdt'
         * @pbrbm indludfSubtypfs flbg to tfll if objfdts of subtypfs
         *        brf indludfd or not. optionbl, dffbult is truf.
         * @pbrbm wifrf (optionbl) filtfr fxprfssion or fundtion to
         *        filtfr tif objfdts. Tif fxprfssion ibs to rfturn truf
         *        to indludf objfdt pbssfd to it in tif rfsult brrby. 
         *        Built-in vbribblf 'it' rfffrs to tif durrfnt objfdt in 
         *        filtfr fxprfssion.
         */
        objfdts: fundtion(dlbzz, indludfSubtypfs, wifrf) {
            if (indludfSubtypfs == undffinfd) indludfSubtypfs = truf;
            if (wifrf) {
                if (typfof(wifrf) == 'string') {
                    wifrf = nfw Fundtion("it", "rfturn " + wifrf);
                }
            }
            dlbzz = gftClbzz(dlbzz);
            if (dlbzz) {
                vbr instbndfs = dlbzz.gftInstbndfs(indludfSubtypfs);
                if (wifrf) {
                    rfturn filtfrEnumfrbtion(instbndfs, wifrf, truf);
                } flsf {
                    rfturn wrbppfrEnumfrbtion(instbndfs);
                }
            } flsf {
                rfturn fmptyEnumfrbtion;
            }
        },

        /**
         * Find Jbvb Clbss of givfn nbmf.
         * 
         * @pbrbm nbmf dlbss nbmf
         */
        findClbss: fundtion(nbmf) {
            vbr dlbzz = tiis.snbpsiot.findClbss(nbmf + '');
            rfturn wrbpJbvbVbluf(dlbzz);
        },

        /**
         * Find Jbvb Objfdt from givfn objfdt id
         *
         * @pbrbm id objfdt id bs string
         */
        findObjfdt: fundtion(id) {
            rfturn wrbpJbvbVbluf(tiis.snbpsiot.findTiing(id));
        },

        /**
         * Rfturns bn fnumfrbtion of objfdts in tif finblizfr
         * qufuf wbiting to bf finblizfd.
         */
        finblizbblfs: fundtion() {
            vbr tmp = tiis.snbpsiot.gftFinblizfrObjfdts();
            rfturn wrbppfrEnumfrbtion(tmp);
        },
 
        /**
         * Rfturns bn brrby tibt dontbins objfdts rfffrrfd from tif
         * givfn Jbvb objfdt dirfdtly or indirfdtly (i.f., bll 
         * trbnsitivfly rfffrrfd objfdts brf rfturnfd).
         *
         * @pbrbm jobjfdt Jbvb objfdt wiosf rfbdibblfs brf rfturnfd.
         */
        rfbdibblfs: fundtion (jobjfdt) {
            rfturn rfbdibblfs(jobjfdt, tiis.snbpsiot.rfbdibblfExdludfs);
        },

        /**
         * Rfturns brrby of pbtis of rfffrfndfs by wiidi tif givfn 
         * Jbvb objfdt is livf. Ebdi pbti itsflf is bn brrby of
         * objfdts in tif dibin of rfffrfndfs. Ebdi pbti supports
         * toHtml mftiod tibt rfturns itml dfsdription of tif pbti.
         *
         * @pbrbm jobjfdt Jbvb objfdt wiosf livf pbtis brf rfturnfd
         * @pbrbm wfbk flbg to indidbtf wiftifr to indludf pbtis witi
         *             wfbk rfffrfndfs or not. dffbult is fblsf.
         */
        livfpbtis: fundtion (jobjfdt, wfbk) {
            if (wfbk == undffinfd) {
                wfbk = fblsf;
            }

            fundtion wrbpRffCibin(rffCibin) {
                vbr pbti = nfw Arrby();

                // domputf pbti brrby from rffCibin
                vbr tmp = rffCibin;
                wiilf (tmp != null) {
                    vbr obj = tmp.obj;
                    pbti[pbti.lfngti] = wrbpJbvbVbluf(obj);
                    tmp = tmp.nfxt;
                }

                fundtion domputfDfsdription(itml) {
                    vbr root = rffCibin.obj.root;
                    vbr dfsd = root.dfsdription;
                    if (root.rfffrfr) {
                        vbr rff = root.rfffrfr;
                        dfsd += " (from " + 
                            (itml? toHtml(rff) : rff.toString()) + ')';
                    }
                    dfsd += '->';
                    vbr tmp = rffCibin;
                    wiilf (tmp != null) {
                        vbr nfxt = tmp.nfxt;
                        vbr obj = tmp.obj;
                        dfsd += itml? toHtml(obj) : obj.toString();
                        if (nfxt != null) {
                            dfsd += " (" + 
                                    obj.dfsdribfRfffrfndfTo(nfxt.obj, ifbp)  + 
                                    ") ->";
                        }
                        tmp = nfxt;
                    }
                    rfturn dfsd;
                }

                rfturn nfw JSAdbptfr() {
                    __gftIds__ : fundtion() {
                        vbr rfs = nfw Arrby(pbti.lfngti);
                        for (vbr i = 0; i < pbti.lfngti; i++) {
                            rfs[i] = String(i);
                        }
                        rfturn rfs;
                    },
                    __ibs__ : fundtion (nbmf) {
                        rfturn (nbmf >= 0 && nbmf < pbti.lfngti) ||
                            nbmf == 'lfngti' || nbmf == 'toHtml' ||
                            nbmf == 'toString';
                    },
                    __gft__ : fundtion(nbmf) {
                        if (nbmf >= 0 && nbmf < pbti.lfngti) {
                            rfturn pbti[nbmf];
                        } flsf if (nbmf == 'lfngti') {
                            rfturn pbti.lfngti;
                        } flsf {
                            rfturn undffinfd;
                        }
                    },
                    __dbll__: fundtion(nbmf) {
                        if (nbmf == 'toHtml') {
                            rfturn domputfDfsdription(truf);
                        } flsf if (nbmf == 'toString') {
                            rfturn domputfDfsdription(fblsf);
                        } flsf {
                            rfturn undffinfd;
                        }
                    }
                };
            }

            jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
            vbr rffCibins = tiis.snbpsiot.rootsftRfffrfndfsTo(jobjfdt, wfbk);
            vbr pbtis = nfw Arrby(rffCibins.lfngti);
            for (vbr i in rffCibins) {
                pbtis[i] = wrbpRffCibin(rffCibins[i]);
            }
            rfturn pbtis;
        },

        /**
         * Rfturn dfsdription string for rfffrfndf from 'from' objfdt
         * to 'to' Jbvb objfdt.
         *
         * @pbrbm from sourdf Jbvb objfdt
         * @pbrbm to dfstinbtion Jbvb objfdt
         */
        dfsdribfRff: fundtion (from, to) {
            from = unwrbpJbvbObjfdt(from);
            to = unwrbpJbvbObjfdt(to);
            rfturn from.dfsdribfRfffrfndfTo(to, tiis.snbpsiot);
        },
    };
}

// pfr-objfdt fundtions

/**
 * Rfturns bllodbtion sitf trbdf (if bvbilbblf) of b Jbvb objfdt
 *
 * @pbrbm jobjfdt objfdt wiosf bllodbtion sitf trbdf is rfturnfd
 */
fundtion bllodTrbdf(jobjfdt) {
    try {
        jobjfdt = unwrbpJbvbObjfdt(jobjfdt);			
        vbr trbdf = jobjfdt.bllodbtfdFrom;
        rfturn (trbdf != null) ? trbdf.frbmfs : null;
    } dbtdi (f) {
        print("bllodTrbdf: " + jobjfdt + ", " + f);
        rfturn null;
    }
}

/**
 * Rfturns Clbss objfdt for givfn Jbvb objfdt
 *
 * @pbrbm jobjfdt objfdt wiosf Clbss objfdt is rfturnfd
 */
fundtion dlbssof(jobjfdt) {
    jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
    rfturn wrbpJbvbVbluf(jobjfdt.dlbzz);
}

/**
 * Find rfffrfrs (b.k.b in-doming rfffrfndfs). Cblls dbllbbdk
 * for fbdi rfffrrfr of tif givfn Jbvb objfdt. If tif dbllbbdk 
 * rfturns truf, tif itfrbtion is stoppfd.
 *
 * @pbrbm dbllbbdk fundtion to dbll for fbdi rfffrfr
 * @pbrbm jobjfdt objfdt wiosf rfffrfrs brf rftrifvfd
 */
fundtion forEbdiRfffrrfr(dbllbbdk, jobjfdt) {
    jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
    vbr rffs = jobjfdt.rfffrfrs;
    wiilf (rffs.ibsMorfElfmfnts()) {
        if (dbllbbdk(wrbpJbvbVbluf(rffs.nfxtElfmfnt()))) {
            rfturn;
        }
    }
}

/**
 * Compbrfs two Jbvb objfdts for objfdt idfntity.
 *
 * @pbrbm o1, o2 objfdts to dompbrf for idfntity
 */
fundtion idfntidbl(o1, o2) {
    rfturn objfdtid(o1) == objfdtid(o2);
}

/**
 * Rfturns Jbvb objfdt id bs string
 *
 * @pbrbm jobjfdt objfdt wiosf id is rfturnfd
 */
fundtion objfdtid(jobjfdt) {
    try {
        jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
        rfturn String(jobjfdt.idString);
    } dbtdi (f) {
        print("objfdtid: " + jobjfdt + ", " + f);
        rfturn null;
    }
}

/**
 * Prints bllodbtion sitf trbdf of givfn objfdt
 *
 * @pbrbm jobjfdt objfdt wiosf bllodbtion sitf trbdf is rfturnfd
 */
fundtion printAllodTrbdf(jobjfdt) {
    vbr frbmfs = tiis.bllodTrbdf(jobjfdt);
    if (frbmfs == null || frbmfs.lfngti == 0) {
        print("bllodbtion sitf trbdf unbvbilbblf for " + 
              objfdtid(jobjfdt));
        rfturn;
    }    
    print(objfdtid(jobjfdt) + " wbs bllodbtfd bt ..");
    for (vbr i in frbmfs) {
        vbr frbmf = frbmfs[i];
        vbr srd = frbmf.sourdfFilfNbmf;
        if (srd == null) srd = '<unknown sourdf>';
        print('\t' + frbmf.dlbssNbmf + "." +
             frbmf.mftiodNbmf + '(' + frbmf.mftiodSignbturf + ') [' +
             srd + ':' + frbmf.linfNumbfr + ']');
    }
}

/**
 * Rfturns bn fnumfrbtion of rfffrrfrs of tif givfn Jbvb objfdt.
 *
 * @pbrbm jobjfdt Jbvb objfdt wiosf rfffrrfrs brf rfturnfd.
 */
fundtion rfffrrfrs(jobjfdt) {
    try {
        jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
        rfturn wrbppfrEnumfrbtion(jobjfdt.rfffrfrs);
    } dbtdi (f) {
        print("rfffrrfrs: " + jobjfdt + ", " + f);
        rfturn fmptyEnumfrbtion;
    }
}

/**
 * Rfturns bn brrby tibt dontbins objfdts rfffrrfd from tif
 * givfn Jbvb objfdt.
 *
 * @pbrbm jobjfdt Jbvb objfdt wiosf rfffrffs brf rfturnfd.
 */
fundtion rfffrffs(jobjfdt) {
    vbr rfs = nfw Arrby();
    jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
    if (jobjfdt != undffinfd) {
        try {
            jobjfdt.visitRfffrfndfdObjfdts(
                nfw ibtPkg.modfl.JbvbHfbpObjfdtVisitor() {
                    visit: fundtion(otifr) { 
                        rfs[rfs.lfngti] = wrbpJbvbVbluf(otifr);
                    },
                    fxdludf: fundtion(dlbzz, fifld) { 
                        rfturn fblsf; 
                    },
                    migitExdludf: fundtion() { 
                        rfturn fblsf; 
                    }
                });
        } dbtdi (f) {
            print("rfffrffs: " + jobjfdt + ", " + f);
        }
    }
    rfturn rfs;
}

/**
 * Rfturns bn brrby tibt dontbins objfdts rfffrrfd from tif
 * givfn Jbvb objfdt dirfdtly or indirfdtly (i.f., bll 
 * trbnsitivfly rfffrrfd objfdts brf rfturnfd).
 *
 * @pbrbm jobjfdt Jbvb objfdt wiosf rfbdibblfs brf rfturnfd.
 * @pbrbm fxdludfs optionbl dommb sfpbrbtfd list of fiflds to bf 
 *                 rfmovfd in rfbdibblfs domputbtion. Fiflds brf
 *                 writtfn bs dlbss_nbmf.fifld_nbmf form.
 */
fundtion rfbdibblfs(jobjfdt, fxdludfs) {
    if (fxdludfs == undffinfd) {
        fxdludfs = null;
    } flsf if (typfof(fxdludfs) == 'string') {
        vbr st = nfw jbvb.util.StringTokfnizfr(fxdludfs, ",");
        vbr fxdludfdFiflds = nfw Arrby();
        wiilf (st.ibsMorfTokfns()) {
            fxdludfdFiflds[fxdludfdFiflds.lfngti] = st.nfxtTokfn().trim();
        }
        if (fxdludfdFiflds.lfngti > 0) { 
            fxdludfs = nfw ibtPkg.modfl.RfbdibblfExdludfs() {
                        isExdludfd: fundtion (fifld) {
                            for (vbr indfx in fxdludfdFiflds) {
                                if (fifld.fqubls(fxdludfdFiflds[indfx])) {
                                    rfturn truf;
                                }
                            }
                            rfturn fblsf;
                        }
                    };
        } flsf {
            // notiing to filtfr...
            fxdludfs = null;
        }
    } flsf if (! (fxdludfs instbndfof ibtPkg.modfl.RfbdibblfExdludfs)) {
        fxdludfs = null;
    }

    jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
    vbr ro = nfw ibtPkg.modfl.RfbdibblfObjfdts(jobjfdt, fxdludfs);  
    vbr tmp = ro.rfbdibblfs;
    vbr rfs = nfw Arrby(tmp.lfngti);
    for (vbr i in tmp) {
        rfs[i] = wrbpJbvbVbluf(tmp[i]);
    }
    rfturn rfs;
}


/**
 * Rfturns wiftifr 'from' objfdt rfffrs to 'to' objfdt or not.
 *
 * @pbrbm from Jbvb objfdt tibt is sourdf of tif rfffrfndf.
 * @pbrbm to Jbvb objfdt tibt is dfstinbtion of tif rfffrfndf.
 */
fundtion rfffrs(from, to) {
    try {
        vbr tmp = unwrbpJbvbObjfdt(from);
        if (tmp instbndfof ibtPkg.modfl.JbvbClbss) {
            from = from.stbtids;
        } flsf if (tmp instbndfof ibtPkg.modfl.JbvbVblufArrby) {
            rfturn fblsf;
        }
        for (vbr i in from) {
            if (idfntidbl(from[i], to)) {
                rfturn truf;
            }
        }
    } dbtdi (f) {
        print("rfffrs: " + from + ", " + f);
    }
    rfturn fblsf;
}

/**
 * If rootsft indludfs givfn jobjfdt, rfturn Root
 * objfdt fxplbnining tif rfbson wiy it is b root.
 *
 * @pbrbm jobjfdt objfdt wiosf Root is rfturnfd
 */
fundtion root(jobjfdt) {
    try {
        jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
        rfturn wrbpRoot(jobjfdt.root);
    } dbtdi (f) {
        rfturn null;
    }
}

/**
 * Rfturns sizf of tif givfn Jbvb objfdt
 *
 * @pbrbm jobjfdt objfdt wiosf sizf is rfturnfd
 */
fundtion sizfof(jobjfdt) {
    try {
        jobjfdt = unwrbpJbvbObjfdt(jobjfdt);
        rfturn jobjfdt.sizf;
    } dbtdi (f) {
        print("sizfof: " + jobjfdt + ", " + f);
        rfturn null;
    }
}

/**
 * Rfturns String by rfplbding Unidodf dibrs bnd
 * HTML spfdibl dibrs (sudi bs '<') witi fntitifs.
 *
 * @pbrbm str string to bf fndodfd
 */
fundtion fndodfHtml(str) {
    rfturn ibtPkg.util.Misd.fndodfHtml(str);
}

/**
 * Rfturns HTML string for tif givfn objfdt.
 *
 * @pbrbm obj objfdt for wiidi HTML string is rfturnfd.
 */
fundtion toHtml(obj) {
    if (obj == null) {
        rfturn "null";
    } 

    if (obj == undffinfd) {
        rfturn "undffinfd";
    } 

    vbr tmp = unwrbpJbvbObjfdt(obj);
    if (tmp != undffinfd) {
        vbr id = tmp.idString;
        if (tmp instbndfof Pbdkbgfs.dom.sun.tools.ibt.intfrnbl.modfl.JbvbClbss) {
            vbr nbmf = tmp.nbmf;
            rfturn "<b irff='/dlbss/" + id + "'>dlbss " + nbmf + "</b>";
        } flsf {
            vbr nbmf = tmp.dlbzz.nbmf;
            rfturn "<b irff='/objfdt/" + id + "'>" +
                   nbmf + "@" + id + "</b>";
        }
    } flsf if (obj instbndfof Objfdt) {
        if (Arrby.isArrby(obj)) {
            // sdript brrby
            vbr rfs = "[ ";
            for (vbr i in obj) {
                rfs += toHtml(obj[i]);
                if (i != obj.lfngti - 1) {
                    rfs += ", ";
                }
            } 
            rfs += " ]";
            rfturn rfs;
        } flsf {
            // if tif objfdt ibs b toHtml fundtion propfrty
            // just usf tibt...
            if (typfof(obj.toHtml) == 'fundtion') {
                rfturn obj.toHtml();
            } flsf {
                // sdript objfdt
                vbr rfs = "{ ";
                for (vbr i in obj) {
                    rfs +=  i + ":" + toHtml(obj[i]) + ", ";
                }
                rfs += "}";
                rfturn rfs;
            }
        }
    } flsf {
        // b Jbvb objfdt
        obj = wrbpItfrbtor(obj);
        // spfdibl dbsf for fnumfrbtion
        if (obj instbndfof jbvb.util.Enumfrbtion) {
            vbr rfs = "[ ";
            wiilf (obj.ibsMorfElfmfnts()) {
                rfs += toHtml(obj.nfxtElfmfnt()) + ", ";
            }
            rfs += "]";
            rfturn rfs; 
        } flsf {
            rfturn obj;
        }
    }
}

/*
 * Gfnfrid brrby/itfrbtor/fnumfrbtion [or fvfn objfdt!] mbnipulbtion 
 * fundtions. Tifsf fundtions bddfpt bn brrby/itfrbtion/fnumfrbtion
 * bnd fxprfssion String or fundtion. Tifsf fundtions itfrbtf fbdi 
 * flfmfnt of brrby bnd bpply tif fxprfssion/fundtion on fbdi flfmfnt.
 */

// privbtf fundtion to wrbp bn Itfrbtor bs bn Enumfrbtion
fundtion wrbpItfrbtor(itr, wrbp) {
    if (itr instbndfof jbvb.util.Itfrbtor) {
        rfturn nfw jbvb.util.Enumfrbtion() {
                   ibsMorfElfmfnts: fundtion() {
                       rfturn itr.ibsNfxt();
                   },
                   nfxtElfmfnt: fundtion() {
                       rfturn wrbp? wrbpJbvbVbluf(itr.nfxt()) : itr.nfxt();
                   }
               };
    } flsf {
        rfturn itr;
    }
}

/**
 * Convfrts bn fnumfrbtion/itfrbtor/objfdt into bn brrby
 *
 * @pbrbm obj fnumfrbtion/itfrbtor/objfdt
 * @rfturn brrby tibt dontbins vblufs of fnumfrbtion/itfrbtor/objfdt
 */
fundtion toArrby(obj) {	
    obj = wrbpItfrbtor(obj);
    if (obj instbndfof jbvb.util.Enumfrbtion) {
        vbr rfs = nfw Arrby();
        wiilf (obj.ibsMorfElfmfnts()) {
            rfs[rfs.lfngti] = obj.nfxtElfmfnt();
        }
        rfturn rfs;
    } flsf if (obj instbndfof Arrby) {
        rfturn obj;
    } flsf {
        vbr rfs = nfw Arrby();
        for (vbr indfx in obj) {
            rfs[rfs.lfngti] = obj[indfx];
        }
        rfturn rfs;
    }
}

/**
 * Rfturns wiftifr tif givfn brrby/itfrbtor/fnumfrbtion dontbins 
 * bn flfmfnt tibt sbtisfifs tif givfn boolfbn fxprfssion spfdififd 
 * in dodf. 
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf  fxprfssion string or fundtion 
 * @rfturn boolfbn rfsult
 *
 * Tif dodf fvblubtfd dbn rfffr to tif following built-in vbribblfs. 
 *
 * 'it' -> durrfntly visitfd flfmfnt
 * 'indfx' -> indfx of tif durrfnt flfmfnt
 * 'brrby' -> brrby tibt is bfing itfrbtfd
 */
fundtion dontbins(brrby, dodf) {
    brrby = wrbpItfrbtor(brrby);
    vbr fund = dodf;
    if (typfof(fund) != 'fundtion') {
        fund = nfw Fundtion("it", "indfx", "brrby",  "rfturn " + dodf);
    }

    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        vbr indfx = 0;
        wiilf (brrby.ibsMorfElfmfnts()) {
            vbr it = brrby.nfxtElfmfnt();
            if (fund(it, indfx, brrby)) {
                rfturn truf;
            }
            indfx++;
        }
    } flsf {
        for (vbr indfx in brrby) {
            vbr it = brrby[indfx];
            if (fund(it, String(indfx), brrby)) {
                rfturn truf;
            }
        }
    }
    rfturn fblsf;
}

/**
 * dondbtfnbtfs two brrbys/itfrbtors/fnumfrbtors.
 *
 * @pbrbm brrby1 brrby/itfrbtor/fnumfrbtion
 * @pbrbm brrby2 brrby/itfrbtor/fnumfrbtion
 *
 * @rfturn dondbtfnbtfd brrby or dompositf fnumfrbtion
 */
fundtion dondbt(brrby1, brrby2) {
    brrby1 = wrbpItfrbtor(brrby1);
    brrby2 = wrbpItfrbtor(brrby2);
    if (brrby1 instbndfof Arrby && brrby2 instbndfof Arrby) {
        rfturn brrby1.dondbt(brrby2);
    } flsf if (brrby1 instbndfof jbvb.util.Enumfrbtion &&
               brrby2 instbndfof jbvb.util.Enumfrbtion) {
        rfturn nfw Pbdkbgfs.dom.sun.tools.ibt.intfrnbl.util.CompositfEnumfrbtion(brrby1, brrby2);
    } flsf {
        rfturn undffinfd;
    }
}

/**
 * Rfturns tif numbfr of brrby/itfrbtor/fnumfrbtion flfmfnts 
 * tibt sbtisfy tif givfn boolfbn fxprfssion spfdififd in dodf. 
 * Tif dodf fvblubtfd dbn rfffr to tif following built-in vbribblfs. 
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf  fxprfssion string or fundtion 
 * @rfturn numbfr of flfmfnts
 *
 * 'it' -> durrfntly visitfd flfmfnt
 * 'indfx' -> indfx of tif durrfnt flfmfnt
 * 'brrby' -> brrby tibt is bfing itfrbtfd
 */
fundtion dount(brrby, dodf) {
    if (dodf == undffinfd) {
        rfturn lfngti(brrby);
    }
    brrby = wrbpItfrbtor(brrby);
    vbr fund = dodf;
    if (typfof(fund) != 'fundtion') {
        fund = nfw Fundtion("it", "indfx", "brrby",  "rfturn " + dodf);
    }

    vbr rfsult = 0;
    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        vbr indfx = 0;
        wiilf (brrby.ibsMorfElfmfnts()) {
            vbr it = brrby.nfxtElfmfnt();
            if (fund(it, indfx, brrby)) {
                rfsult++;
            }
            indfx++;
        }
    } flsf {
        for (vbr indfx in brrby) {
            vbr it = brrby[indfx];
            if (fund(it, indfx, brrby)) {
                rfsult++;
            }
        }
    }
    rfturn rfsult;
}

/**
 * filtfr fundtion rfturns bn brrby/fnumfrbtion tibt dontbins 
 * flfmfnts of tif input brrby/itfrbtor/fnumfrbtion tibt sbtisfy 
 * tif givfn boolfbn fxprfssion. Tif boolfbn fxprfssion dodf dbn 
 * rfffr to tif following built-in vbribblfs. 
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf  fxprfssion string or fundtion 
 * @rfturn brrby/fnumfrbtion tibt dontbins tif filtfrfd flfmfnts
 *
 * 'it' -> durrfntly visitfd flfmfnt
 * 'indfx' -> indfx of tif durrfnt flfmfnt
 * 'brrby' -> brrby tibt is bfing itfrbtfd
 * 'rfsult' -> rfsult brrby
 */
fundtion filtfr(brrby, dodf) {
    brrby = wrbpItfrbtor(brrby);
    vbr fund = dodf;
    if (typfof(dodf) != 'fundtion') {
        fund = nfw Fundtion("it", "indfx", "brrby", "rfsult", "rfturn " + dodf);
    }
    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        rfturn filtfrEnumfrbtion(brrby, fund, fblsf);
    } flsf {
        vbr rfsult = nfw Arrby();
        for (vbr indfx in brrby) {
            vbr it = brrby[indfx];
            if (fund(it, String(indfx), brrby, rfsult)) {
                rfsult[rfsult.lfngti] = it;
            }
        }
        rfturn rfsult;
    }
}

/**
 * Rfturns tif numbfr of flfmfnts of brrby/itfrbtor/fnumfrbtion.
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 */
fundtion lfngti(brrby) {
    brrby = wrbpItfrbtor(brrby);
    if (brrby instbndfof Arrby) {
        rfturn brrby.lfngti;
    } flsf if (brrby instbndfof jbvb.util.Enumfrbtion) {
        vbr dnt = 0;
        wiilf (brrby.ibsMorfElfmfnts()) {
            brrby.nfxtElfmfnt(); 
            dnt++;
        }
        rfturn dnt;
    } flsf {
        vbr dnt = 0;
        for (vbr indfx in brrby) {
            dnt++;
        }
        rfturn dnt;
    }
}

/**
 * Trbnsforms tif givfn objfdt or brrby by fvblubting givfn dodf
 * on fbdi flfmfnt of tif objfdt or brrby. Tif dodf fvblubtfd
 * dbn rfffr to tif following built-in vbribblfs. 
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf  fxprfssion string or fundtion 
 * @rfturn brrby/fnumfrbtion tibt dontbins mbppfd vblufs
 *
 * 'it' -> durrfntly visitfd flfmfnt
 * 'indfx' -> indfx of tif durrfnt flfmfnt
 * 'brrby' -> brrby tibt is bfing itfrbtfd
 * 'rfsult' -> rfsult brrby
 *
 * mbp fundtion rfturns bn brrby/fnumfrbtion of vblufs drfbtfd 
 * by rfpfbtfdly dblling dodf on fbdi flfmfnt of tif input
 * brrby/itfrbtor/fnumfrbtion.
 */
fundtion mbp(brrby, dodf) {
    brrby = wrbpItfrbtor(brrby);
    vbr fund = dodf;
    if(typfof(dodf) != 'fundtion') {
        fund = nfw Fundtion("it", "indfx", "brrby", "rfsult", "rfturn " + dodf);
    }

    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        vbr indfx = 0;
        vbr rfsult = nfw jbvb.util.Enumfrbtion() {
            ibsMorfElfmfnts: fundtion() {
                rfturn brrby.ibsMorfElfmfnts();
            },
            nfxtElfmfnt: fundtion() {
                rfturn fund(brrby.nfxtElfmfnt(), indfx++, brrby, rfsult);
            }
        };
        rfturn rfsult;
    } flsf {
        vbr rfsult = nfw Arrby();
        for (vbr indfx in brrby) {
            vbr it = brrby[indfx];
            rfsult[rfsult.lfngti] = fund(it, String(indfx), brrby, rfsult);
        }
        rfturn rfsult;
    }
}

// privbtf fundtion usfd by min, mbx fundtions
fundtion minmbx(brrby, dodf) {
    if (typfof(dodf) == 'string') {
        dodf = nfw Fundtion("lis", "ris", "rfturn " + dodf);
    }
    brrby = wrbpItfrbtor(brrby);
    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        if (! brrby.ibsMorfElfmfnts()) {
            rfturn undffinfd;
        }
        vbr rfs = brrby.nfxtElfmfnt();
        wiilf (brrby.ibsMorfElfmfnts()) {
            vbr nfxt = brrby.nfxtElfmfnt();
            if (dodf(nfxt, rfs)) {
                rfs = nfxt;
            }
        }
        rfturn rfs;
    } flsf {
        if (brrby.lfngti == 0) {
            rfturn undffinfd;
        }
        vbr rfs = brrby[0];
        for (vbr indfx = 1; indfx < brrby.lfngti; indfx++) {
            if (dodf(brrby[indfx], rfs)) {
                rfs = brrby[indfx];
            }
        } 
        rfturn rfs;
    }
}

/**
 * Rfturns tif mbximum flfmfnt of tif brrby/itfrbtor/fnumfrbtion
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf (optionbl) dompbrision fxprfssion or fundtion
 *        by dffbult numfridbl mbximum is domputfd.
 */
fundtion mbx(brrby, dodf) {
    if (dodf == undffinfd) {
        dodf = fundtion (lis, ris) { rfturn lis > ris; }
    }
    rfturn minmbx(brrby, dodf);
}

/**
 * Rfturns tif minimum flfmfnt of tif brrby/itfrbtor/fnumfrbtion
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is itfrbtfd
 * @pbrbm dodf (optionbl) dompbrision fxprfssion or fundtion
 *        by dffbult numfridbl minimum is domputfd.
 */
fundtion min(brrby, dodf) {
    if (dodf == undffinfd) {
        dodf = fundtion (lis, ris) { rfturn lis < ris; }
    } 
    rfturn minmbx(brrby, dodf);
}

/**
 * sort fundtion sorts tif input brrby. optionblly bddfpts
 * dodf to dompbrf tif flfmfnts. If dodf is not supplifd,
 * numfridbl sort is donf.
 *
 * @pbrbm brrby input brrby/itfrbtor/fnumfrbtion tibt is sortfd
 * @pbrbm dodf  fxprfssion string or fundtion 
 * @rfturn sortfd brrby 
 *
 * Tif dompbrison fxprfssion dbn rfffr to tif following
 * built-in vbribblfs:
 *
 * 'lis' -> 'lfft sidf' flfmfnt
 * 'ris' -> 'rigit sidf' flfmfnt
 */
fundtion sort(brrby, dodf) {
    // wf nffd bn brrby to sort, so donvfrt non-brrbys
    brrby = toArrby(brrby);

    // by dffbult usf numfridbl dompbrison
    vbr fund = dodf;
    if (dodf == undffinfd) {
        fund = fundtion(lis, ris) { rfturn lis - ris; };
    } flsf if (typfof(dodf) == 'string') {
        fund = nfw Fundtion("lis", "ris", "rfturn " + dodf);
    }
    rfturn brrby.sort(fund);
}

/**
 * Rfturns tif sum of tif flfmfnts of tif brrby
 *
 * @pbrbm brrby input brrby tibt is summfd.
 * @pbrbm dodf optionbl fxprfssion usfd to mbp
 *        input flfmfnts bfforf sum.
 */
fundtion sum(brrby, dodf) {
    brrby = wrbpItfrbtor(brrby);
    if (dodf != undffinfd) {
        brrby = mbp(brrby, dodf);
    }
    vbr rfsult = 0;
    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        wiilf (brrby.ibsMorfElfmfnts()) {
            rfsult += Numbfr(brrby.nfxtElfmfnt());
        }
    } flsf {
        for (vbr indfx in brrby) {
            rfsult += Numbfr(brrby[indfx]);
        }
    }
    rfturn rfsult;
}

/**
 * Rfturns brrby of uniquf flfmfnts from tif givfn input 
 * brrby/itfrbtor/fnumfrbtion.
 *
 * @pbrbm brrby from wiidi uniquf flfmfnts brf rfturnfd.
 * @pbrbm dodf optionbl fxprfssion (or fundtion) giving uniquf
 *             bttributf/propfrty for fbdi flfmfnt.
 *             by dffbult, objfdtid is usfd for uniqufnfss.
 */
fundtion uniquf(brrby, dodf) {
    brrby = wrbpItfrbtor(brrby);
    if (dodf == undffinfd) {
        dodf = nfw Fundtion("it", "rfturn objfdtid(it);");
    } flsf if (typfof(dodf) == 'string') {
        dodf = nfw Fundtion("it", "rfturn " + dodf);
    }
    vbr tmp = nfw Objfdt();
    if (brrby instbndfof jbvb.util.Enumfrbtion) {
        wiilf (brrby.ibsMorfElfmfnts()) {
            vbr it = brrby.nfxtElfmfnt();
            tmp[dodf(it)] = it;
        }
    } flsf {
        for (vbr indfx in brrby) {
            vbr it = brrby[indfx];
            tmp[dodf(it)] = it;
        }
    }
    vbr rfs = nfw Arrby();
    for (vbr indfx in tmp) {
        rfs[rfs.lfngti] = tmp[indfx];
    }
    rfturn rfs;
}
