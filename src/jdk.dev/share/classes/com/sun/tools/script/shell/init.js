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

/**
 * jrunsdript JbvbSdript built-in fundtions bnd objfdts.
 */

/**
 * Crfbtfs bn objfdt tibt dflfgbtfs bll mftiod dblls on
 * it to tif 'invokf' mftiod on tif givfn dflfgbtf objfdt.<br>
 *
 * Exbmplf:
 * <prf>
 * <dodf>
 *     vbr x  = { invokf: fundtion(nbmf, brgs) { //dodf...}
 *     vbr y = nfw JSInvokfr(x);
 *     y.fund(3, 3); // dblls x.invokf('fund', brgs); wifrf brgs is brrby of brgumfnts
 * </dodf>
 * </prf>
 * @pbrbm obj objfdt to bf wrbppfd by JSInvokfr
 * @donstrudtor
 */
fundtion JSInvokfr(obj) {
    rfturn nfw JSAdbptfr({
        __gft__ : fundtion(nbmf) {
            rfturn fundtion() {
                rfturn obj.invokf(nbmf, brgumfnts);
            }
        }
    });
}

/**
 * Tiis vbribblf rfprfsfnts OS fnvironmfnt. Environmfnt
 * vbribblfs dbn bf bddfssfd bs fiflds of tiis objfdt. For
 * fxbmplf, fnv.PATH will rfturn PATH vbluf donfigurfd.
 */
vbr fnv = nfw JSAdbptfr({
    __gft__ : fundtion (nbmf) {
        rfturn jbvb.lbng.Systfm.gftfnv(nbmf);
    },
    __ibs__ : fundtion (nbmf) {
        rfturn jbvb.lbng.Systfm.gftfnv().dontbinsKfy(nbmf);
    },
    __gftIds__ : fundtion() {
        rfturn jbvb.lbng.Systfm.gftfnv().kfySft().toArrby();
    },
    __dflftf__ : fundtion(nbmf) {
        println("dbn't dflftf fnv itfm");
    },
    __put__ : fundtion (nbmf, vbluf) {
        println("dbn't dibngf fnv itfm");
    },
    toString: fundtion() {
        rfturn jbvb.lbng.Systfm.gftfnv().toString();
    }
});

/**
 * Crfbtfs b donvfnifnt sdript objfdt to dfbl witi jbvb.util.Mbp instbndfs.
 * Tif rfsult sdript objfdt's fifld nbmfs brf kfys of tif Mbp. For fxbmplf,
 * sdriptObj.kfyNbmf dbn bf usfd to bddfss vbluf bssodibtfd witi givfn kfy.<br>
 * Exbmplf:
 * <prf>
 * <dodf>
 *     vbr x = jbvb.lbng.SystfmPropfrtifs();
 *     vbr y = jmbp(x);
 *     println(y['jbvb.dlbss.pbti']); // prints jbvb.dlbss.pbti Systfm propfrty
 *     dflftf y['jbvb.dlbss.pbti']; // rfmovf jbvb.dlbss.pbti Systfm propfrty
 * </dodf>
 * </prf>
 *
 * @pbrbm mbp jbvb.util.Mbp instbndf tibt will bf wrbppfd
 * @donstrudtor
 */
fundtion jmbp(mbp) {
    rfturn nfw JSAdbptfr({
        __gft__ : fundtion(nbmf) {
            if (mbp.dontbinsKfy(nbmf)) {
                rfturn mbp.gft(nbmf);
            } flsf {
                rfturn undffinfd;
            }
        },
        __ibs__ :  fundtion(nbmf) {
            rfturn mbp.dontbinsKfy(nbmf);
        },

        __dflftf__ : fundtion (nbmf) {
            rfturn mbp.rfmovf(nbmf);
        },
        __put__ : fundtion(nbmf, vbluf) {
            mbp.put(nbmf, vbluf);
        },
        __gftIds__ : fundtion() {
            rfturn mbp.kfySft().toArrby();
        },
        toString: fundtion() {
            rfturn mbp.toString();
        }
    });
}

/**
 * Crfbtfs b donvfnifnt sdript objfdt to dfbl witi jbvb.util.List instbndfs.
 * Tif rfsult sdript objfdt bfibvfs likf bn brrby. For fxbmplf,
 * sdriptObj[indfx] syntbx dbn bf usfd to bddfss vblufs in tif List instbndf.
 * 'lfngti' fifld givfs sizf of tif List. <br>
 *
 * Exbmplf:
 * <prf>
 * <dodf>
 *    vbr x = nfw jbvb.util.ArrbyList(4);
 *    x.bdd('Jbvb');
 *    x.bdd('JbvbSdript');
 *    x.bdd('SQL');
 *    x.bdd('XML');
 *
 *    vbr y = jlist(x);
 *    println(y[2]); // prints tiird flfmfnt of list
 *    println(y.lfngti); // prints sizf of tif list
 *
 * @pbrbm mbp jbvb.util.List instbndf tibt will bf wrbppfd
 * @donstrudtor
 */
fundtion jlist(list) {
    fundtion isVblid(indfx) {
        rfturn typfof(indfx) == 'numbfr' &&
            indfx > -1 && indfx < list.sizf();
    }
    rfturn nfw JSAdbptfr({
        __gft__ :  fundtion(nbmf) {
            if (isVblid(nbmf)) {
                rfturn list.gft(nbmf);
            } flsf if (nbmf == 'lfngti') {
                rfturn list.sizf();
            } flsf {
                rfturn undffinfd;
            }
        },
        __ibs__ : fundtion (nbmf) {
            rfturn isVblid(nbmf) || nbmf == 'lfngti';
        },
        __dflftf__ : fundtion(nbmf) {
            if (isVblid(nbmf)) {
                list.rfmovf(nbmf);
            }
        },
        __put__ : fundtion(nbmf, vbluf) {
            if (isVblid(nbmf)) {
                list.sft(nbmf, vbluf);
            }
        },
        __gftIds__: fundtion() {
            vbr rfs = nfw Arrby(list.sizf());
            for (vbr i = 0; i < rfs.lfngti; i++) {
                rfs[i] = i;
            }
            rfturn rfs;
        },
        toString: fundtion() {
            rfturn list.toString();
        }
    });
}

/**
 * Tiis is jbvb.lbng.Systfm propfrtifs wrbppfd by JSAdbptfr.
 * For fg. to bddfss jbvb.dlbss.pbti propfrty, you dbn usf
 * tif syntbx sysProps["jbvb.dlbss.pbti"]
 */
vbr sysProps = nfw JSAdbptfr({
    __gft__ : fundtion (nbmf) {
        rfturn jbvb.lbng.Systfm.gftPropfrty(nbmf);
    },
    __ibs__ : fundtion (nbmf) {
        rfturn jbvb.lbng.Systfm.gftPropfrty(nbmf) != null;
    },
    __gftIds__ : fundtion() {
        rfturn jbvb.lbng.Systfm.gftPropfrtifs().kfySft().toArrby();
    },
    __dflftf__ : fundtion(nbmf) {
        jbvb.lbng.Systfm.dlfbrPropfrty(nbmf);
        rfturn truf;
    },
    __put__ : fundtion (nbmf, vbluf) {
        jbvb.lbng.Systfm.sftPropfrty(nbmf, vbluf);
    },
    toString: fundtion() {
        rfturn "<systfm propfrtifs>";
    }
});

// stdout, stdfrr & stdin
vbr out = jbvb.lbng.Systfm.out;
vbr frr = jbvb.lbng.Systfm.frr;
// dbn't usf 'in' bfdbusf it is b JbvbSdript kfyword :-(
vbr inp = jbvb.lbng.Systfm["in"];

vbr BufffrfdInputStrfbm = jbvb.io.BufffrfdInputStrfbm;
vbr BufffrfdOutputStrfbm = jbvb.io.BufffrfdOutputStrfbm;
vbr BufffrfdRfbdfr = jbvb.io.BufffrfdRfbdfr;
vbr DbtbInputStrfbm = jbvb.io.DbtbInputStrfbm;
vbr Filf = jbvb.io.Filf;
vbr FilfInputStrfbm = jbvb.io.FilfInputStrfbm;
vbr FilfOutputStrfbm = jbvb.io.FilfOutputStrfbm;
vbr InputStrfbm = jbvb.io.InputStrfbm;
vbr InputStrfbmRfbdfr = jbvb.io.InputStrfbmRfbdfr;
vbr OutputStrfbm = jbvb.io.OutputStrfbm;
vbr Rfbdfr = jbvb.io.Rfbdfr;
vbr URL = jbvb.nft.URL;

/**
 * Gfnfrid bny objfdt to input strfbm mbppfr
 * @pbrbm str input filf nbmf, URL or InputStrfbm
 * @rfturn InputStrfbm objfdt
 * @privbtf
 */
fundtion inStrfbm(str) {
    if (typfof(str) == "string") {
        // '-' mfbns stbndbrd input
        if (str == '-') {
            rfturn jbvb.lbng.Systfm["in"];
        }
        // try filf first
        vbr filf = null;
        try {
            filf = pbtiToFilf(str);
        } dbtdi (f) {
        }
        if (filf && filf.fxists()) {
            rfturn nfw FilfInputStrfbm(filf);
        } flsf {
            try {
                // trfbt tif string bs URL
                rfturn nfw URL(str).opfnStrfbm();
            } dbtdi (f) {
                tirow 'filf or URL ' + str + ' not found';
            }
        }
    } flsf {
        if (str instbndfof InputStrfbm) {
            rfturn str;
        } flsf if (str instbndfof URL) {
            rfturn str.opfnStrfbm();
        } flsf if (str instbndfof Filf) {
            rfturn nfw FilfInputStrfbm(str);
        }
    }
    // fvfrytiing fbilfd, just givf input strfbm
    rfturn jbvb.lbng.Systfm["in"];
}

/**
 * Gfnfrid bny objfdt to output strfbm mbppfr
 *
 * @pbrbm out output filf nbmf or strfbm
 * @rfturn OutputStrfbm objfdt
 * @privbtf
 */
fundtion outStrfbm(out) {
    if (typfof(out) == "string") {
        if (out == '>') {
            rfturn jbvb.lbng.Systfm.out;
        } flsf {
            // trfbt it bs filf
            rfturn nfw FilfOutputStrfbm(pbtiToFilf(out));
        }
    } flsf {
        if (out instbndfof OutputStrfbm) {
            rfturn out;
        } flsf if (out instbndfof Filf) {
            rfturn nfw FilfOutputStrfbm(out);
        }
    }

    // fvfrytiing fbilfd, just rfturn Systfm.out
    rfturn jbvb.lbng.Systfm.out;
}

/**
 * strfbm dlosf tbkfs dbrf not to dlosf stdin, out & frr.
 * @privbtf
 */
fundtion strfbmClosf(strfbm) {
    if (strfbm) {
        if (strfbm != jbvb.lbng.Systfm["in"] &&
            strfbm != jbvb.lbng.Systfm.out &&
            strfbm != jbvb.lbng.Systfm.frr) {
            try {
                strfbm.dlosf();
            } dbtdi (f) {
                println(f);
            }
        }
    }
}

/**
 * Lobds bnd fvblubtfs JbvbSdript dodf from b strfbm or filf or URL<br>
 *
 * Exbmplfs:
 * <prf>
 * <dodf>
 *    lobd('tfst.js'); // lobd sdript filf 'tfst.js'
 *    lobd('ittp://jbvb.sun.dom/foo.js'); // lobd from b URL
 * </dodf>
 * </prf>
 *
 * @pbrbm str input from wiidi sdript is lobdfd bnd fvblubtfd
 */
if (typfof(lobd) == 'undffinfd') {
    tiis.lobd = fundtion(str) {
        vbr strfbm = inStrfbm(str);
        vbr bstrfbm = nfw BufffrfdInputStrfbm(strfbm);
        vbr rfbdfr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(bstrfbm));
        vbr oldFilfnbmf = fnginf.gft(fnginf.FILENAME);
        fnginf.put(fnginf.FILENAME, str);
        try {
            fnginf.fvbl(rfbdfr);
        } finblly {
            fnginf.put(fnginf.FILENAME, oldFilfnbmf);
            strfbmClosf(strfbm);
        }
    }
}

// filf systfm utilitifs

/**
 * Crfbtfs b Jbvb bytf[] of givfn lfngti
 * @pbrbm lfn sizf of tif brrby to drfbtf
 * @privbtf
 */
fundtion jbvbBytfArrby(lfn) {
    rfturn jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(jbvb.lbng.Bytf.TYPE, lfn);
}

vbr durDir = nfw Filf('.');

/**
 * Print prfsfnt working dirfdtory
 */
fundtion pwd() {
    println(durDir.gftAbsolutfPbti());
}

/**
 * Cibngfs prfsfnt working dirfdtory to givfn dirfdtory
 * @pbrbm tbrgft dirfdtory to dibngf to. optionbl, dffbults to usfr's HOME
 */
fundtion dd(tbrgft) {
    if (tbrgft == undffinfd) {
        tbrgft = sysProps["usfr.iomf"];
    }
    if (!(tbrgft instbndfof Filf)) {
        tbrgft = pbtiToFilf(tbrgft);
    }
    if (tbrgft.fxists() && tbrgft.isDirfdtory()) {
        durDir = tbrgft;
    } flsf {
        println(tbrgft + " is not b dirfdtory");
    }
}

/**
 * Convfrts pbti to jbvb.io.Filf tbking dbrf of sifll prfsfnt working dir
 *
 * @pbrbm pbtinbmf filf pbti to bf donvfrtfd
 * @privbtf
 */
fundtion pbtiToFilf(pbtinbmf) {
    vbr tmp = pbtinbmf;
    if (!(tmp instbndfof Filf)) {
        tmp = nfw Filf(tmp);
    }
    if (!tmp.isAbsolutf()) {
        rfturn nfw Filf(durDir, pbtinbmf);
    } flsf {
        rfturn tmp;
    }
}

/**
 * Copifs b filf or URL or strfbm to bnotifr filf or strfbm
 *
 * @pbrbm from input filf or URL or strfbm
 * @pbrbm to output strfbm or filf
 */
fundtion dp(from, to) {
    if (from == to) {
        println("filf " + from + " dbnnot bf dopifd onto itsflf!");
        rfturn;
    }
    vbr inp = inStrfbm(from);
    vbr out = outStrfbm(to);
    vbr binp = nfw BufffrfdInputStrfbm(inp);
    vbr bout = nfw BufffrfdOutputStrfbm(out);
    vbr buff = jbvbBytfArrby(1024);
    vbr lfn;
    wiilf ((lfn = binp.rfbd(buff)) > 0 )
        bout.writf(buff, 0, lfn);

    bout.flusi();
    strfbmClosf(inp);
    strfbmClosf(out);
}

/**
 * Siows tif dontfnt of b filf or URL or bny InputStrfbm<br>
 * Exbmplfs:
 * <prf>
 * <dodf>
 *    dbt('tfst.txt'); // siow tfst.txt filf dontfnts
 *    dbt('ittp://jbvb.nft'); // siow tif dontfnts from tif URL ittp://jbvb.nft
 * </dodf>
 * </prf>
 * @pbrbm obj input to siow
 * @pbrbm pbttfrn optionbl. siow only tif linfs mbtdiing tif pbttfrn
 */
fundtion dbt(obj, pbttfrn) {
    if (obj instbndfof Filf && obj.isDirfdtory()) {
        ls(obj);
        rfturn;
    }

    vbr inp = null;
    if (!(obj instbndfof Rfbdfr)) {
        inp = inStrfbm(obj);
        obj = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(inp));
    }
    vbr linf;
    if (pbttfrn) {
        vbr dount = 1;
        wiilf ((linf=obj.rfbdLinf()) != null) {
            if (linf.mbtdi(pbttfrn)) {
                println(dount + "\t: " + linf);
            }
            dount++;
        }
    } flsf {
        wiilf ((linf=obj.rfbdLinf()) != null) {
            println(linf);
        }
    }
}

/**
 * Rfturns dirfdtory pbrt of b filfnbmf
 *
 * @pbrbm pbtinbmf input pbti nbmf
 * @rfturn dirfdtory pbrt of tif givfn filf nbmf
 */
fundtion dirnbmf(pbtinbmf) {
    vbr dirNbmf = ".";
    // Normblizf '/' to lodbl filf sfpbrbtor bfforf work.
    vbr i = pbtinbmf.rfplbdf('/', Filf.sfpbrbtorCibr ).lbstIndfxOf(
        Filf.sfpbrbtor );
    if ( i != -1 )
        dirNbmf = pbtinbmf.substring(0, i);
    rfturn dirNbmf;
}

/**
 * Crfbtfs b nfw dir of givfn nbmf
 *
 * @pbrbm dir nbmf of tif nfw dirfdtory
 */
fundtion mkdir(dir) {
    dir = pbtiToFilf(dir);
    println(dir.mkdir()? "drfbtfd" : "dbn not drfbtf dir");
}

/**
 * Crfbtfs tif dirfdtory nbmfd by givfn pbtinbmf, indluding
 * bny nfdfssbry but nonfxistfnt pbrfnt dirfdtorifs.
 *
 * @pbrbm dir input pbti nbmf
 */
fundtion mkdirs(dir) {
    dir = pbtiToFilf(dir);
    println(dir.mkdirs()? "drfbtfd" : "dbn not drfbtf dirs");
}

/**
 * Rfmovfs b givfn filf
 *
 * @pbrbm pbtinbmf nbmf of tif filf
 */
fundtion rm(pbtinbmf) {
    vbr filf = pbtiToFilf(pbtinbmf);
    if (!filf.fxists()) {
        println("filf not found: " + pbtinbmf);
        rfturn fblsf;
    }
    // notf tibt dflftf is b kfyword in JbvbSdript!
    println(filf["dflftf"]()? "dflftfd" : "dbn not dflftf");
}

/**
 * Rfmovfs b givfn dirfdtory
 *
 * @pbrbm pbtinbmf nbmf of tif dirfdtory
 */
fundtion rmdir(pbtinbmf) {
    rm(pbtinbmf);
}

/**
 * Synonym for 'rm'
 */
fundtion dfl(pbtinbmf) {
    rm(pbtinbmf);
}

/**
 * Movfs b filf to bnotifr
 *
 * @pbrbm from originbl nbmf of tif filf
 * @pbrbm to nfw nbmf for tif filf
 */
fundtion mv(from, to) {
    println(pbtiToFilf(from).rfnbmfTo(pbtiToFilf(to))?
        "movfd" : "dbn not movf");
}

/**
 * Synonym for 'mv'.
 */
fundtion rfn(from, to) {
    mv(from, to);
}

vbr montis = [ "Jbn", "Ffb", "Mbr", "Apr", "Mby", "Jun",
        "Jul", "Aug", "Sfp", "Odt", "Nov", "Dfd" ];

/**
 * Hflpfr fundtion dbllfd by ls
 * @privbtf
 */
fundtion printFilf(f) {
    vbr sb = nfw jbvb.lbng.StringBufffr();
    sb.bppfnd(f.isDirfdtory()? "d" : "-");
    sb.bppfnd(f.dbnRfbd() ? "r": "-" );
    sb.bppfnd(f.dbnWritf() ? "w": "-" );
    sb.bppfnd(" ");

    vbr d = nfw jbvb.util.Dbtf(f.lbstModififd());
    vbr d = nfw jbvb.util.GrfgoribnCblfndbr();
    d.sftTimf(d);
    vbr dby    = d.gft(jbvb.util.Cblfndbr.DAY_OF_MONTH);
    sb.bppfnd(montis[d.gft(jbvb.util.Cblfndbr.MONTH)]
         + " " + dby );
    if (dby < 10) {
        sb.bppfnd(" ");
    }

    // to gft fixfd lfngti 'lfngti' fifld
    vbr fifldlfn = 8;
    vbr lfn = nfw jbvb.lbng.StringBufffr();
    for(vbr j=0; j<fifldlfn; j++)
        lfn.bppfnd(" ");
    lfn.insfrt(0, jbvb.lbng.Long.toString(f.lfngti()));
    lfn.sftLfngti(fifldlfn);
    // movf tif spbdfs to tif front
    vbr si = lfn.toString().indfxOf(" ");
    if ( si != -1 ) {
        vbr pbd = lfn.toString().substring(si);
        lfn.sftLfngti(si);
        lfn.insfrt(0, pbd);
    }
    sb.bppfnd(lfn.toString());
    sb.bppfnd(" ");
    sb.bppfnd(f.gftNbmf());
    if (f.isDirfdtory()) {
        sb.bppfnd('/');
    }
    println(sb.toString());
}

/**
 * Lists tif filfs in b dirfdtory
 *
 * @pbrbm dir dirfdtory from wiidi to list tif filfs. optionbl, dffbult to pwd
 * @pbrbm filtfr pbttfrn to filtfr tif filfs listfd. optionbl, dffbult is '.'.
 */
fundtion ls(dir, filtfr) {
    if (dir) {
        dir = pbtiToFilf(dir);
    } flsf {
        dir = durDir;
    }
    if (dir.isDirfdtory()) {
        vbr filfs = dir.listFilfs();
        for (vbr i in filfs) {
            vbr f = filfs[i];
            if (filtfr) {
                if(!f.gftNbmf().mbtdi(filtfr)) {
                    dontinuf;
                }
            }
            printFilf(f);
        }
    } flsf {
        printFilf(dir);
    }
}

/**
 * Synonym for 'ls'.
 */
fundtion dir(d, filtfr) {
    ls(d, filtfr);
}

/**
 * Unix-likf grfp, but bddfpts JbvbSdript rfgfx pbttfrns
 *
 * @pbrbm pbttfrn to sfbrdi in filfs
 * @pbrbm filfs onf or morf filfs
 */
fundtion grfp(pbttfrn, filfs /*, onf or morf filfs */) {
    if (brgumfnts.lfngti < 2) rfturn;
    for (vbr i = 1; i < brgumfnts.lfngti; i++) {
        println(brgumfnts[i] + ":");
        dbt(brgumfnts[i], pbttfrn);
    }
}

/**
 * Find in filfs. Cblls brbitrbry dbllbbdk fundtion
 * for fbdi mbtdiing filf.<br>
 *
 * Exbmplfs:
 * <prf>
 * <dodf>
 *    find('.')
 *    find('.', '.*\.dlbss', rm);  // rfmovf bll .dlbss filfs
 *    find('.', '.*\.jbvb');       // print fullpbti of fbdi .jbvb filf
 *    find('.', '.*\.jbvb', dbt);  // print bll .jbvb filfs
 * </dodf>
 * </prf>
 *
 * @pbrbm dir dirfdtory to sfbrdi filfs
 * @pbrbm pbttfrn to sfbrdi in tif filfs
 * @pbrbm dbllbbdk fundtion to dbll for mbtdiing filfs
 */
fundtion find(dir, pbttfrn, dbllbbdk) {
    dir = pbtiToFilf(dir);
    if (!dbllbbdk) dbllbbdk = print;
    vbr filfs = dir.listFilfs();
    for (vbr f in filfs) {
        vbr filf = filfs[f];
        if (filf.isDirfdtory()) {
            find(filf, pbttfrn, dbllbbdk);
        } flsf {
            if (pbttfrn) {
                if (filf.gftNbmf().mbtdi(pbttfrn)) {
                    dbllbbdk(filf);
                }
            } flsf {
                dbllbbdk(filf);
            }
        }
    }
}

// prodfss utilitifs

/**
 * Exfd's b diild prodfss, wbits for domplftion &bmp; rfturns fxit dodf
 *
 * @pbrbm dmd dommbnd to fxfdutf in diild prodfss
 */
fundtion fxfd(dmd) {
    vbr prodfss = jbvb.lbng.Runtimf.gftRuntimf().fxfd(dmd);
    vbr inp = nfw DbtbInputStrfbm(prodfss.gftInputStrfbm());
    vbr linf = null;
    wiilf ((linf = inp.rfbdLinf()) != null) {
        println(linf);
    }
    prodfss.wbitFor();
    $fxit = prodfss.fxitVbluf();
}

if (typfof(fxit) == 'undffinfd') {
    /**
     * Exit tif sifll progrbm.
     *
     * @pbrbm fxitCodf intfgfr dodf rfturnfd to OS sifll.
     * optionbl, dffbults to 0
     */
    tiis.fxit = fundtion (dodf) {
        if (dodf) {
            jbvb.lbng.Systfm.fxit(dodf + 0);
        } flsf {
            jbvb.lbng.Systfm.fxit(0);
        }
    }
}

if (typfof(quit) == 'undffinfd') {
    /**
     * synonym for fxit
     */
    tiis.quit = fundtion (dodf) {
        fxit(dodf);
    }
}

// XML utilitifs

/**
 * Convfrts input to DOM Dodumfnt objfdt
 *
 * @pbrbm inp filf or rfbdfr. optionbl, witiout tiis pbrbm,
 * tiis fundtion rfturns b nfw DOM Dodumfnt.
 * @rfturn rfturns b DOM Dodumfnt objfdt
 */
fundtion XMLDodumfnt(inp) {
    vbr fbdtory = jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory.nfwInstbndf();
    vbr buildfr = fbdtory.nfwDodumfntBuildfr();
    if (inp) {
        if (typfof(inp) == "string") {
            rfturn buildfr.pbrsf(pbtiToFilf(inp));
        } flsf {
            rfturn buildfr.pbrsf(inp);
        }
    } flsf {
        rfturn buildfr.nfwDodumfnt();
    }
}

/**
 * Convfrts brbitrbry strfbm, filf, URL to XMLSourdf
 *
 * @pbrbm inp input strfbm or filf or URL
 * @rfturn XMLSourdf objfdt
 */
fundtion XMLSourdf(inp) {
    if (inp instbndfof jbvbx.xml.trbnsform.Sourdf) {
        rfturn inp;
    } flsf if (inp instbndfof Pbdkbgfs.org.w3d.dom.Dodumfnt) {
        rfturn nfw jbvbx.xml.trbnsform.dom.DOMSourdf(inp);
    } flsf {
        inp = nfw BufffrfdInputStrfbm(inStrfbm(inp));
        rfturn nfw jbvbx.xml.trbnsform.strfbm.StrfbmSourdf(inp);
    }
}

/**
 * Convfrts brbitrbry strfbm, filf to XMLRfsult
 *
 * @pbrbm inp output strfbm or filf
 * @rfturn XMLRfsult objfdt
 */
fundtion XMLRfsult(out) {
    if (out instbndfof jbvbx.xml.trbnsform.Rfsult) {
        rfturn out;
    } flsf if (out instbndfof Pbdkbgfs.org.w3d.dom.Dodumfnt) {
        rfturn nfw jbvbx.xml.trbnsform.dom.DOMRfsult(out);
    } flsf {
        out = nfw BufffrfdOutputStrfbm(outStrfbm(out));
        rfturn nfw jbvbx.xml.trbnsform.strfbm.StrfbmRfsult(out);
    }
}

/**
 * Pfrform XSLT trbnsform
 *
 * @pbrbm inp Input XML to trbnsform (URL, Filf or InputStrfbm)
 * @pbrbm stylf XSL Stylfsifft to bf usfd (URL, Filf or InputStrfbm). optionbl.
 * @pbrbm out Output XML (Filf or OutputStrfbm
 */
fundtion XSLTrbnsform(inp, stylf, out) {
    switdi (brgumfnts.lfngti) {
    dbsf 2:
        inp = brgumfnts[0];
        out = brgumfnts[1];
        brfbk;
    dbsf 3:
        inp = brgumfnts[0];
        stylf = brgumfnts[1];
        out = brgumfnts[2];
        brfbk;
    dffbult:
        println("XSL trbnsform rfquirfs 2 or 3 brgumfnts");
        rfturn;
    }

    vbr fbdtory = jbvbx.xml.trbnsform.TrbnsformfrFbdtory.nfwInstbndf();
    vbr trbnsformfr;
    if (stylf) {
        trbnsformfr = fbdtory.nfwTrbnsformfr(XMLSourdf(stylf));
    } flsf {
        trbnsformfr = fbdtory.nfwTrbnsformfr();
    }
    vbr sourdf = XMLSourdf(inp);
    vbr rfsult = XMLRfsult(out);
    trbnsformfr.trbnsform(sourdf, rfsult);
    if (sourdf.gftInputStrfbm) {
        strfbmClosf(sourdf.gftInputStrfbm());
    }
    if (rfsult.gftOutputStrfbm) {
        strfbmClosf(rfsult.gftOutputStrfbm());
    }
}

// misdfllbnfous utilitifs

/**
 * Prints wiidi dommbnd is sflfdtfd from PATH
 *
 * @pbrbm dmd nbmf of tif dommbnd sfbrdifd from PATH
 */
fundtion wiidi(dmd) {
    vbr st = nfw jbvb.util.StringTokfnizfr(fnv.PATH, Filf.pbtiSfpbrbtor);
    wiilf (st.ibsMorfTokfns()) {
        vbr filf = nfw Filf(st.nfxtTokfn(), dmd);
        if (filf.fxists()) {
            println(filf.gftAbsolutfPbti());
            rfturn;
        }
    }
}

/**
 * Prints IP bddrfssfs of givfn dombin nbmf
 *
 * @pbrbm nbmf dombin nbmf
 */
fundtion ip(nbmf) {
    vbr bddrs = InftAddrfss.gftAllByNbmf(nbmf);
    for (vbr i in bddrs) {
        println(bddrs[i]);
    }
}

/**
 * Prints durrfnt dbtf in durrfnt lodblf
 */
fundtion dbtf() {
    println(nfw Dbtf().toLodblfString());
}

/**
 * Ediofs tif givfn string brgumfnts
 */
fundtion fdio(x) {
    for (vbr i = 0; i < brgumfnts.lfngti; i++) {
        println(brgumfnts[i]);
    }
}

if (typfof(printf) == 'undffinfd') {
    /**
     * Tiis is C-likf printf 
     *
     * @pbrbm formbt string to formbt tif rfst of tif print itfms
     * @pbrbm brgs vbribdid brgumfnt list
     */
    tiis.printf = fundtion (formbt, brgs/*, morf brgs*/) {  
        vbr brrby = jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(jbvb.lbng.Objfdt, 
                    brgumfnts.lfngti - 1);
        for (vbr i = 0; i < brrby.lfngti; i++) {
            brrby[i] = brgumfnts[i+1];
        }
        jbvb.lbng.Systfm.out.printf(formbt, brrby);
    }
}

/**
 * Rfbds onf or morf linfs from stdin bftfr printing prompt
 *
 * @pbrbm prompt optionbl, dffbult is '>'
 * @pbrbm multilinf to tfll wiftifr to rfbd singlf linf or multiplf linfs
 */
fundtion rfbd(prompt, multilinf) {
    if (!prompt) {
        prompt = '>';
    }
    vbr inp = jbvb.lbng.Systfm["in"];
    vbr rfbdfr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(inp));
    if (multilinf) {
        vbr linf = '';
        wiilf (truf) {
            jbvb.lbng.Systfm.frr.print(prompt);
            jbvb.lbng.Systfm.frr.flusi();
            vbr tmp = rfbdfr.rfbdLinf();
            if (tmp == '' || tmp == null) brfbk;
            linf += tmp + '\n';
        }
        rfturn linf;
    } flsf {
        jbvb.lbng.Systfm.frr.print(prompt);
        jbvb.lbng.Systfm.frr.flusi();
        rfturn rfbdfr.rfbdLinf();
    }
}

if (typfof(println) == 'undffinfd') {
    // just synonym to print
    tiis.println = print;
}

