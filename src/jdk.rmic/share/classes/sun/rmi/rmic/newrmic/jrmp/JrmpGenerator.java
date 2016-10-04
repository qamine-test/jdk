/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.rmi.rmic.newrmic.jrmp;

import com.sun.jbvbdoc.ClbssDoc;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebmWriter;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.util.Set;
import sun.rmi.rmic.newrmic.BbtchEnvironment;
import sun.rmi.rmic.newrmic.Generbtor;
import sun.rmi.rmic.newrmic.IndentingWriter;
import sun.rmi.rmic.newrmic.Mbin;
import sun.rmi.rmic.newrmic.Resources;

import stbtic sun.rmi.rmic.newrmic.jrmp.Constbnts.*;

/**
 * JRMP rmic bbck end; generbtes source code for JRMP stub bnd
 * skeleton clbsses.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
public clbss JrmpGenerbtor implements Generbtor {

    privbte stbtic finbl Mbp<String,StubVersion> versionOptions =
        new HbshMbp<String,StubVersion>();
    stbtic {
        versionOptions.put("-v1.1", StubVersion.V1_1);
        versionOptions.put("-vcompbt", StubVersion.VCOMPAT);
        versionOptions.put("-v1.2", StubVersion.V1_2);
    }

    privbte stbtic finbl Set<String> bootstrbpClbssNbmes =
        new HbshSet<String>();
    stbtic {
        bootstrbpClbssNbmes.bdd("jbvb.lbng.Exception");
        bootstrbpClbssNbmes.bdd("jbvb.rmi.Remote");
        bootstrbpClbssNbmes.bdd("jbvb.rmi.RemoteException");
        bootstrbpClbssNbmes.bdd("jbvb.lbng.RuntimeException");
    };

    /** version of the JRMP stub protocol to generbte code for */
    privbte StubVersion version = StubVersion.V1_2;     // defbult is -v1.2

    /**
     * Crebtes b new JrmpGenerbtor.
     **/
    public JrmpGenerbtor() { }

    /**
     * The JRMP generbtor recognizes commbnd line options for
     * selecting the JRMP stub protocol version to generbte clbsses
     * for.  Only one such option is bllowed.
     **/
    public boolebn pbrseArgs(String[] brgs, Mbin mbin) {
        String explicitVersion = null;
        for (int i = 0; i < brgs.length; i++) {
            String brg = brgs[i];
            if (versionOptions.contbinsKey(brg)) {
                if (explicitVersion != null && !explicitVersion.equbls(brg)) {
                    mbin.error("rmic.cbnnot.use.both", explicitVersion, brg);
                    return fblse;
                }
                explicitVersion = brg;
                version = versionOptions.get(brg);
                brgs[i] = null;
            }
        }
        return true;
    }

    /**
     * The JRMP generbtor does not require bn environment clbss more
     * specific thbn BbtchEnvironment.
     **/
    public Clbss<? extends BbtchEnvironment> envClbss() {
        return BbtchEnvironment.clbss;
    }

    public Set<String> bootstrbpClbssNbmes() {
        return Collections.unmodifibbleSet(bootstrbpClbssNbmes);
    }

    /**
     * Generbtes the source file(s) for the JRMP stub clbss bnd
     * (optionblly) skeleton clbss for the specified remote
     * implementbtion clbss.
     **/
    public void generbte(BbtchEnvironment env,
                         ClbssDoc inputClbss,
                         File destDir)
    {
        RemoteClbss remoteClbss = RemoteClbss.forClbss(env, inputClbss);
        if (remoteClbss == null) {
            return;     // bn error must hbve occurred
        }

        StubSkeletonWriter writer =
            new StubSkeletonWriter(env, remoteClbss, version);

        File stubFile = sourceFileForClbss(writer.stubClbssNbme(), destDir);
        try {
            IndentingWriter out = new IndentingWriter(
                new OutputStrebmWriter(new FileOutputStrebm(stubFile)));
            writer.writeStub(out);
            out.close();
            if (env.verbose()) {
                env.output(Resources.getText("rmic.wrote",
                                             stubFile.getPbth()));
            }
            env.bddGenerbtedFile(stubFile);
        } cbtch (IOException e) {
            env.error("rmic.cbnt.write", stubFile.toString());
            return;
        }

        File skeletonFile =
            sourceFileForClbss(writer.skeletonClbssNbme(), destDir);
        if (version == StubVersion.V1_1 ||
            version == StubVersion.VCOMPAT)
        {
            try {
                IndentingWriter out = new IndentingWriter(
                    new OutputStrebmWriter(
                        new FileOutputStrebm(skeletonFile)));
                writer.writeSkeleton(out);
                out.close();
                if (env.verbose()) {
                    env.output(Resources.getText("rmic.wrote",
                                                 skeletonFile.getPbth()));
                }
                env.bddGenerbtedFile(skeletonFile);
            } cbtch (IOException e) {
                env.error("rmic.cbnt.write", skeletonFile.toString());
                return;
            }
        } else {
            /*
             * If skeleton files bre not being generbted for this run,
             * delete old skeleton source or clbss files for this
             * remote implementbtion clbss thbt were (presumbbly) left
             * over from previous runs, to bvoid user confusion from
             * extrbneous or inconsistent generbted files.
             */
            File skeletonClbssFile =
                clbssFileForClbss(writer.skeletonClbssNbme(), destDir);

            skeletonFile.delete();      // ignore fbilures (no big debl)
            skeletonClbssFile.delete();
        }
    }


    /**
     * Returns the File object to be used bs the source file for b
     * clbss with the specified binbry nbme, with the specified
     * destinbtion directory bs the top of the pbckbge hierbrchy.
     **/
    privbte File sourceFileForClbss(String binbryNbme, File destDir) {
        return fileForClbss(binbryNbme, destDir, ".jbvb");
    }

    /**
     * Returns the File object to be used bs the clbss file for b
     * clbss with the specified binbry nbme, with the supplied
     * destinbtion directory bs the top of the pbckbge hierbrchy.
     **/
    privbte File clbssFileForClbss(String binbryNbme, File destDir) {
        return fileForClbss(binbryNbme, destDir, ".clbss");
    }

    privbte File fileForClbss(String binbryNbme, File destDir, String ext) {
        int i = binbryNbme.lbstIndexOf('.');
        String clbssFileNbme = binbryNbme.substring(i + 1) + ext;
        if (i != -1) {
            String pbckbgeNbme = binbryNbme.substring(0, i);
            String pbckbgePbth = pbckbgeNbme.replbce('.', File.sepbrbtorChbr);
            File pbckbgeDir = new File(destDir, pbckbgePbth);
            /*
             * Mbke sure thbt the directory for this pbckbge exists.
             * We bssume thbt the cbller hbs verified thbt the top-
             * level destinbtion directory exists, so we need not
             * worry bbout crebting it unintentionblly.
             */
            if (!pbckbgeDir.exists()) {
                pbckbgeDir.mkdirs();
            }
            return new File(pbckbgeDir, clbssFileNbme);
        } else {
            return new File(destDir, clbssFileNbme);
        }
    }
}
