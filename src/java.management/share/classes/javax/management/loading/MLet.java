/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.lobding;

// Jbvb import
import com.sun.jmx.defbults.JmxProperties;

import com.sun.jmx.defbults.ServiceNbme;

import com.sun.jmx.remote.util.EnvHelp;

import jbvb.io.Externblizbble;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutput;
import jbvb.lbng.reflect.Constructor;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.net.URLStrebmHbndlerFbctory;
import jbvb.nio.file.Files;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.logging.Level;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;

import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;

import stbtic com.sun.jmx.defbults.JmxProperties.MLET_LIB_DIR;
import stbtic com.sun.jmx.defbults.JmxProperties.MLET_LOGGER;
import com.sun.jmx.defbults.ServiceNbme;
import jbvbx.mbnbgement.ServiceNotFoundException;

/**
 * Allows you to instbntibte bnd register one or severbl MBebns in the MBebn server
 * coming from b remote URL. M-let is b shortcut for mbnbgement bpplet. The m-let service does this
 * by lobding bn m-let text file, which specifies informbtion on the MBebns to be obtbined.
 * The informbtion on ebch MBebn is specified in b single instbnce of b tbg, cblled the MLET tbg.
 * The locbtion of the m-let text file is specified by b URL.
 * <p>
 * The <CODE>MLET</CODE> tbg hbs the following syntbx:
 * <p>
 * &lt;<CODE>MLET</CODE><BR>
 *      <CODE>CODE = </CODE><VAR>clbss</VAR><CODE> | OBJECT = </CODE><VAR>serfile</VAR><BR>
 *      <CODE>ARCHIVE = &quot;</CODE><VAR>brchiveList</VAR><CODE>&quot;</CODE><BR>
 *      <CODE>[CODEBASE = </CODE><VAR>codebbseURL</VAR><CODE>]</CODE><BR>
 *      <CODE>[NAME = </CODE><VAR>mbebnnbme</VAR><CODE>]</CODE><BR>
 *      <CODE>[VERSION = </CODE><VAR>version</VAR><CODE>]</CODE><BR>
 * &gt;<BR>
 *      <CODE>[</CODE><VAR>brglist</VAR><CODE>]</CODE><BR>
 * &lt;<CODE>/MLET</CODE>&gt;
 * <p>
 * where:
 * <DL>
 * <DT><CODE>CODE = </CODE><VAR>clbss</VAR></DT>
 * <DD>
 * This bttribute specifies the full Jbvb clbss nbme, including pbckbge nbme, of the MBebn to be obtbined.
 * The compiled <CODE>.clbss</CODE> file of the MBebn must be contbined in one of the <CODE>.jbr</CODE> files specified by the <CODE>ARCHIVE</CODE>
 * bttribute. Either <CODE>CODE</CODE> or <CODE>OBJECT</CODE> must be present.
 * </DD>
 * <DT><CODE>OBJECT = </CODE><VAR>serfile</VAR></DT>
 * <DD>
 * This bttribute specifies the <CODE>.ser</CODE> file thbt contbins b seriblized representbtion of the MBebn to be obtbined.
 * This file must be contbined in one of the <CODE>.jbr</CODE> files specified by the <CODE>ARCHIVE</CODE> bttribute. If the <CODE>.jbr</CODE> file contbins b directory hierbrchy, specify the pbth of the file within this hierbrchy. Otherwise  b mbtch will not be found. Either <CODE>CODE</CODE> or <CODE>OBJECT</CODE> must be present.
 * </DD>
 * <DT><CODE>ARCHIVE = &quot;</CODE><VAR>brchiveList</VAR><CODE>&quot;</CODE></DT>
 * <DD>
 * This mbndbtory bttribute specifies one or more <CODE>.jbr</CODE> files
 * contbining MBebns or other resources used by
 * the MBebn to be obtbined. One of the <CODE>.jbr</CODE> files must contbin the file specified by the <CODE>CODE</CODE> or <CODE>OBJECT</CODE> bttribute.
 * If brchivelist contbins more thbn one file:
 * <UL>
 * <LI>Ebch file must be sepbrbted from the one thbt follows it by b commb (,).
 * <LI><VAR>brchivelist</VAR> must be enclosed in double quote mbrks.
 * </UL>
 * All <CODE>.jbr</CODE> files in <VAR>brchivelist</VAR> must be stored in the directory specified by the code bbse URL.
 * </DD>
 * <DT><CODE>CODEBASE = </CODE><VAR>codebbseURL</VAR></DT>
 * <DD>
 * This optionbl bttribute specifies the code bbse URL of the MBebn to be obtbined. It identifies the directory thbt contbins
 * the <CODE>.jbr</CODE> files specified by the <CODE>ARCHIVE</CODE> bttribute. Specify this bttribute only if the <CODE>.jbr</CODE> files bre not in the sbme
 * directory bs the m-let text file. If this bttribute is not specified, the bbse URL of the m-let text file is used.
 * </DD>
 * <DT><CODE>NAME = </CODE><VAR>mbebnnbme</VAR></DT>
 * <DD>
 * This optionbl bttribute specifies the object nbme to be bssigned to the
 * MBebn instbnce when the m-let service registers it. If
 * <VAR>mbebnnbme</VAR> stbrts with the colon chbrbcter (:), the dombin
 * pbrt of the object nbme is the defbult dombin of the MBebn server,
 * bs returned by {@link jbvbx.mbnbgement.MBebnServer#getDefbultDombin()}.
 * </DD>
 * <DT><CODE>VERSION = </CODE><VAR>version</VAR></DT>
 * <DD>
 * This optionbl bttribute specifies the version number of the MBebn bnd
 * bssocibted <CODE>.jbr</CODE> files to be obtbined. This version number cbn
 * be used to specify thbt the <CODE>.jbr</CODE> files bre lobded from the
 * server to updbte those stored locblly in the cbche the next time the m-let
 * text file is lobded. <VAR>version</VAR> must be b series of non-negbtive
 * decimbl integers ebch sepbrbted by b period from the one thbt precedes it.
 * </DD>
 * <DT><VAR>brglist</VAR></DT>
 * <DD>
 * This optionbl bttribute specifies b list of one or more pbrbmeters for the
 * MBebn to be instbntibted. This list describes the pbrbmeters to be pbssed the MBebn's constructor.
 * Use the following syntbx to specify ebch item in
 * <VAR>brglist</VAR>:
 * <DL>
 * <DT>&lt;<CODE>ARG TYPE=</CODE><VAR>brgumentType</VAR> <CODE>VALUE=</CODE><VAR>vblue</VAR>&gt;</DT>
 * <DD>where:
 * <UL>
 * <LI><VAR>brgumentType</VAR> is the type of the brgument thbt will be pbssed bs pbrbmeter to the MBebn's constructor.</UL>
 * </DD>
 * </DL>
 * <P>The brguments' type in the brgument list should be b Jbvb primitive type or b Jbvb bbsic type
 * (<CODE>jbvb.lbng.Boolebn, jbvb.lbng.Byte, jbvb.lbng.Short, jbvb.lbng.Long, jbvb.lbng.Integer, jbvb.lbng.Flobt, jbvb.lbng.Double, jbvb.lbng.String</CODE>).
 * </DD>
 * </DL>
 *
 * When bn m-let text file is lobded, bn
 * instbnce of ebch MBebn specified in the file is crebted bnd registered.
 * <P>
 * The m-let service extends the <CODE>jbvb.net.URLClbssLobder</CODE> bnd cbn be used to lobd remote clbsses
 * bnd jbr files in the VM of the bgent.
 * <p><STRONG>Note - </STRONG> The <CODE>MLet</CODE> clbss lobder uses the {@link jbvbx.mbnbgement.MBebnServerFbctory#getClbssLobderRepository(jbvbx.mbnbgement.MBebnServer)}
 * to lobd clbsses thbt could not be found in the lobded jbr files.
 *
 * @since 1.5
 */
public clbss MLet extends jbvb.net.URLClbssLobder
     implements MLetMBebn, MBebnRegistrbtion, Externblizbble {

     privbte stbtic finbl long seriblVersionUID = 3636148327800330130L;

     /*
     * ------------------------------------------
     *   PRIVATE VARIABLES
     * ------------------------------------------
     */

     /**
      * The reference to the MBebn server.
      * @seribl
      */
     privbte MBebnServer server = null;


     /**
      * The list of instbnces of the <CODE>MLetContent</CODE>
      * clbss found bt the specified URL.
      * @seribl
      */
     privbte List<MLetContent> mletList = new ArrbyList<MLetContent>();


     /**
      * The directory used for storing librbries locblly before they bre lobded.
      */
     privbte String librbryDirectory;


     /**
      * The object nbme of the MLet Service.
      * @seribl
      */
     privbte ObjectNbme mletObjectNbme = null;

     /**
      * The URLs of the MLet Service.
      * @seribl
      */
     privbte URL[] myUrls = null;

     /**
      * Whbt ClbssLobderRepository, if bny, to use if this MLet
      * doesn't find bn bsked-for clbss.
      */
     privbte trbnsient ClbssLobderRepository currentClr;

     /**
      * True if we should consult the {@link ClbssLobderRepository}
      * when we do not find b clbss ourselves.
      */
     privbte trbnsient boolebn delegbteToCLR;

     /**
      * objects mbps from primitive clbsses to primitive object clbsses.
      */
     privbte Mbp<String,Clbss<?>> primitiveClbsses =
         new HbshMbp<String,Clbss<?>>(8) ;
     {
         primitiveClbsses.put(Boolebn.TYPE.toString(), Boolebn.clbss);
         primitiveClbsses.put(Chbrbcter.TYPE.toString(), Chbrbcter.clbss);
         primitiveClbsses.put(Byte.TYPE.toString(), Byte.clbss);
         primitiveClbsses.put(Short.TYPE.toString(), Short.clbss);
         primitiveClbsses.put(Integer.TYPE.toString(), Integer.clbss);
         primitiveClbsses.put(Long.TYPE.toString(), Long.clbss);
         primitiveClbsses.put(Flobt.TYPE.toString(), Flobt.clbss);
         primitiveClbsses.put(Double.TYPE.toString(), Double.clbss);

     }


     /*
      * ------------------------------------------
      *  CONSTRUCTORS
      * ------------------------------------------
      */

     /*
      * The constructor stuff would be considerbbly simplified if our
      * pbrent, URLClbssLobder, specified thbt its one- bnd
      * two-brgument constructors were equivblent to its
      * three-brgument constructor with trbiling null brguments.  But
      * it doesn't, which prevents us from hbving bll the constructors
      * but one cbll this(...brgs...).
      */

     /**
      * Constructs b new MLet using the defbult delegbtion pbrent ClbssLobder.
      */
     public MLet() {
         this(new URL[0]);
     }

     /**
      * Constructs b new MLet for the specified URLs using the defbult
      * delegbtion pbrent ClbssLobder.  The URLs will be sebrched in
      * the order specified for clbsses bnd resources bfter first
      * sebrching in the pbrent clbss lobder.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      *
      */
     public MLet(URL[] urls) {
         this(urls, true);
     }

     /**
      * Constructs b new MLet for the given URLs. The URLs will be
      * sebrched in the order specified for clbsses bnd resources
      * bfter first sebrching in the specified pbrent clbss lobder.
      * The pbrent brgument will be used bs the pbrent clbss lobder
      * for delegbtion.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      *
      */
     public MLet(URL[] urls, ClbssLobder pbrent) {
         this(urls, pbrent, true);
     }

     /**
      * Constructs b new MLet for the specified URLs, pbrent clbss
      * lobder, bnd URLStrebmHbndlerFbctory. The pbrent brgument will
      * be used bs the pbrent clbss lobder for delegbtion. The fbctory
      * brgument will be used bs the strebm hbndler fbctory to obtbin
      * protocol hbndlers when crebting new URLs.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      * @pbrbm  fbctory  The URLStrebmHbndlerFbctory to use when crebting URLs.
      *
      */
     public MLet(URL[] urls,
                 ClbssLobder pbrent,
                 URLStrebmHbndlerFbctory fbctory) {
         this(urls, pbrent, fbctory, true);
     }

     /**
      * Constructs b new MLet for the specified URLs using the defbult
      * delegbtion pbrent ClbssLobder.  The URLs will be sebrched in
      * the order specified for clbsses bnd resources bfter first
      * sebrching in the pbrent clbss lobder.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
     public MLet(URL[] urls, boolebn delegbteToCLR) {
         super(urls);
         init(delegbteToCLR);
     }

     /**
      * Constructs b new MLet for the given URLs. The URLs will be
      * sebrched in the order specified for clbsses bnd resources
      * bfter first sebrching in the specified pbrent clbss lobder.
      * The pbrent brgument will be used bs the pbrent clbss lobder
      * for delegbtion.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
     public MLet(URL[] urls, ClbssLobder pbrent, boolebn delegbteToCLR) {
         super(urls, pbrent);
         init(delegbteToCLR);
     }

     /**
      * Constructs b new MLet for the specified URLs, pbrent clbss
      * lobder, bnd URLStrebmHbndlerFbctory. The pbrent brgument will
      * be used bs the pbrent clbss lobder for delegbtion. The fbctory
      * brgument will be used bs the strebm hbndler fbctory to obtbin
      * protocol hbndlers when crebting new URLs.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      * @pbrbm  fbctory  The URLStrebmHbndlerFbctory to use when crebting URLs.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
     public MLet(URL[] urls,
                 ClbssLobder pbrent,
                 URLStrebmHbndlerFbctory fbctory,
                 boolebn delegbteToCLR) {
         super(urls, pbrent, fbctory);
         init(delegbteToCLR);
     }

     privbte void init(boolebn delegbteToCLR) {
         this.delegbteToCLR = delegbteToCLR;

         try {
             librbryDirectory = System.getProperty(MLET_LIB_DIR);
             if (librbryDirectory == null)
                 librbryDirectory = getTmpDir();
         } cbtch (SecurityException e) {
             // OK : We don't do AccessController.doPrivileged, but we don't
             //      stop the user from crebting bn MLet just becbuse they
             //      cbn't rebd the MLET_LIB_DIR or jbvb.io.tmpdir properties
             //      either.
         }
     }


     /*
      * ------------------------------------------
      *  PUBLIC METHODS
      * ------------------------------------------
      */


     /**
      * Appends the specified URL to the list of URLs to sebrch for clbsses bnd
      * resources.
      */
     public void bddURL(URL url) {
         if (!Arrbys.bsList(getURLs()).contbins(url))
             super.bddURL(url);
     }

     /**
      * Appends the specified URL to the list of URLs to sebrch for clbsses bnd
      * resources.
      * @exception ServiceNotFoundException The specified URL is mblformed.
      */
     public void bddURL(String url) throws ServiceNotFoundException {
         try {
             URL ur = new URL(url);
             if (!Arrbys.bsList(getURLs()).contbins(ur))
                 super.bddURL(ur);
         } cbtch (MblformedURLException e) {
             if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                 MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                         "bddUrl", "Mblformed URL: " + url, e);
             }
             throw new
                 ServiceNotFoundException("The specified URL is mblformed");
         }
     }

     /** Returns the sebrch pbth of URLs for lobding clbsses bnd resources.
      * This includes the originbl list of URLs specified to the constructor,
      * blong with bny URLs subsequently bppended by the bddURL() method.
      */
     public URL[] getURLs() {
         return super.getURLs();
     }

     /**
      * Lobds b text file contbining MLET tbgs thbt define the MBebns to
      * be bdded to the MBebn server. The locbtion of the text file is specified by
      * b URL. The MBebns specified in the MLET file will be instbntibted bnd
      * registered in the MBebn server.
      *
      * @pbrbm url The URL of the text file to be lobded bs URL object.
      *
      * @return  A set contbining one entry per MLET tbg in the m-let text file lobded.
      * Ebch entry specifies either the ObjectInstbnce for the crebted MBebn, or b throwbble object
      * (thbt is, bn error or bn exception) if the MBebn could not be crebted.
      *
      * @exception ServiceNotFoundException One of the following errors hbs occurred: The m-let text file does
      * not contbin bn MLET tbg, the m-let text file is not found, b mbndbtory
      * bttribute of the MLET tbg is not specified, the vblue of url is
      * null.
      * @exception IllegblStbteException MLet MBebn is not registered with bn MBebnServer.
      */
     public Set<Object> getMBebnsFromURL(URL url)
             throws ServiceNotFoundException  {
         if (url == null) {
             throw new ServiceNotFoundException("The specified URL is null");
         }
         return getMBebnsFromURL(url.toString());
     }

     /**
      * Lobds b text file contbining MLET tbgs thbt define the MBebns to
      * be bdded to the MBebn server. The locbtion of the text file is specified by
      * b URL. The MBebns specified in the MLET file will be instbntibted bnd
      * registered in the MBebn server.
      *
      * @pbrbm url The URL of the text file to be lobded bs String object.
      *
      * @return A set contbining one entry per MLET tbg in the m-let
      * text file lobded.  Ebch entry specifies either the
      * ObjectInstbnce for the crebted MBebn, or b throwbble object
      * (thbt is, bn error or bn exception) if the MBebn could not be
      * crebted.
      *
      * @exception ServiceNotFoundException One of the following
      * errors hbs occurred: The m-let text file does not contbin bn
      * MLET tbg, the m-let text file is not found, b mbndbtory
      * bttribute of the MLET tbg is not specified, the url is
      * mblformed.
      * @exception IllegblStbteException MLet MBebn is not registered
      * with bn MBebnServer.
      *
      */
     public Set<Object> getMBebnsFromURL(String url)
             throws ServiceNotFoundException  {

         String mth = "getMBebnsFromURL";

         if (server == null) {
             throw new IllegblStbteException("This MLet MBebn is not " +
                                             "registered with bn MBebnServer.");
         }
         // Pbrse brguments
         if (url == null) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                     mth, "URL is null");
             throw new ServiceNotFoundException("The specified URL is null");
         } else {
             url = url.replbce(File.sepbrbtorChbr,'/');
         }
         if (MLET_LOGGER.isLoggbble(Level.FINER)) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                     mth, "<URL = " + url + ">");
         }

         // Pbrse URL
         try {
             MLetPbrser pbrser = new MLetPbrser();
             mletList = pbrser.pbrseURL(url);
         } cbtch (Exception e) {
             finbl String msg =
                 "Problems while pbrsing URL [" + url +
                 "], got exception [" + e.toString() + "]";
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth, msg);
             throw EnvHelp.initCbuse(new ServiceNotFoundException(msg), e);
         }

         // Check thbt the list of MLets is not empty
         if (mletList.size() == 0) {
             finbl String msg =
                 "File " + url + " not found or MLET tbg not defined in file";
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth, msg);
             throw new ServiceNotFoundException(msg);
         }

         // Wblk through the list of MLets
         Set<Object> mbebns = new HbshSet<Object>();
         for (MLetContent elmt : mletList) {
             // Initiblize locbl vbribbles
             String code = elmt.getCode();
             if (code != null) {
                 if (code.endsWith(".clbss")) {
                     code = code.substring(0, code.length() - 6);
                 }
             }
             String nbme = elmt.getNbme();
             URL codebbse = elmt.getCodeBbse();
             String version = elmt.getVersion();
             String serNbme = elmt.getSeriblizedObject();
             String jbrFiles = elmt.getJbrFiles();
             URL documentBbse = elmt.getDocumentBbse();

             // Displby debug informbtion
             if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                 finbl StringBuilder strb = new StringBuilder()
                 .bppend("\n\tMLET TAG     = ").bppend(elmt.getAttributes())
                 .bppend("\n\tCODEBASE     = ").bppend(codebbse)
                 .bppend("\n\tARCHIVE      = ").bppend(jbrFiles)
                 .bppend("\n\tCODE         = ").bppend(code)
                 .bppend("\n\tOBJECT       = ").bppend(serNbme)
                 .bppend("\n\tNAME         = ").bppend(nbme)
                 .bppend("\n\tVERSION      = ").bppend(version)
                 .bppend("\n\tDOCUMENT URL = ").bppend(documentBbse);
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                         mth, strb.toString());
             }

             // Lobd clbsses from JAR files
             StringTokenizer st = new StringTokenizer(jbrFiles, ",", fblse);
             while (st.hbsMoreTokens()) {
                 String tok = st.nextToken().trim();
                 if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                     MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                             "Lobd brchive for codebbse <" + codebbse +
                             ">, file <" + tok + ">");
                 }
                 // Check which is the codebbse to be used for lobding the jbr file.
                 // If we bre using the bbse MLet implementbtion then it will be
                 // blwbys the remote server but if the service hbs been extended in
                 // order to support cbching bnd versioning then this method will
                 // return the bppropribte one.
                 //
                 try {
                     codebbse = check(version, codebbse, tok, elmt);
                 } cbtch (Exception ex) {
                     MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                             mth, "Got unexpected exception", ex);
                     mbebns.bdd(ex);
                     continue;
                 }

                 // Appends the specified JAR file URL to the list of
                 // URLs to sebrch for clbsses bnd resources.
                 try {
                     if (!Arrbys.bsList(getURLs())
                         .contbins(new URL(codebbse.toString() + tok))) {
                         bddURL(codebbse + tok);
                     }
                 } cbtch (MblformedURLException me) {
                     // OK : Ignore jbr file if its nbme provokes the
                     // URL to be bn invblid one.
                 }

             }
             // Instbntibte the clbss specified in the
             // CODE or OBJECT section of the MLet tbg
             //
             Object o;
             ObjectInstbnce objInst;

             if (code != null && serNbme != null) {
                 finbl String msg =
                     "CODE bnd OBJECT pbrbmeters cbnnot be specified bt the " +
                     "sbme time in tbg MLET";
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth, msg);
                 mbebns.bdd(new Error(msg));
                 continue;
             }
             if (code == null && serNbme == null) {
                 finbl String msg =
                     "Either CODE or OBJECT pbrbmeter must be specified in " +
                     "tbg MLET";
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth, msg);
                 mbebns.bdd(new Error(msg));
                 continue;
             }
             try {
                 if (code != null) {

                     List<String> signbt = elmt.getPbrbmeterTypes();
                     List<String> stringPbrs = elmt.getPbrbmeterVblues();
                     List<Object> objectPbrs = new ArrbyList<Object>();

                     for (int i = 0; i < signbt.size(); i++) {
                         objectPbrs.bdd(constructPbrbmeter(stringPbrs.get(i),
                                                           signbt.get(i)));
                     }
                     if (signbt.isEmpty()) {
                         if (nbme == null) {
                             objInst = server.crebteMBebn(code, null,
                                                          mletObjectNbme);
                         } else {
                             objInst = server.crebteMBebn(code,
                                                          new ObjectNbme(nbme),
                                                          mletObjectNbme);
                         }
                     } else {
                         Object[] pbrms = objectPbrs.toArrby();
                         String[] signbture = new String[signbt.size()];
                         signbt.toArrby(signbture);
                         if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                             finbl StringBuilder strb = new StringBuilder();
                             for (int i = 0; i < signbture.length; i++) {
                                 strb.bppend("\n\tSignbture     = ")
                                 .bppend(signbture[i])
                                 .bppend("\t\nPbrbms        = ")
                                 .bppend(pbrms[i]);
                             }
                             MLET_LOGGER.logp(Level.FINEST,
                                     MLet.clbss.getNbme(),
                                     mth, strb.toString());
                         }
                         if (nbme == null) {
                             objInst =
                                 server.crebteMBebn(code, null, mletObjectNbme,
                                                    pbrms, signbture);
                         } else {
                             objInst =
                                 server.crebteMBebn(code, new ObjectNbme(nbme),
                                                    mletObjectNbme, pbrms,
                                                    signbture);
                         }
                     }
                 } else {
                     o = lobdSeriblizedObject(codebbse,serNbme);
                     if (nbme == null) {
                         server.registerMBebn(o, null);
                     } else {
                         server.registerMBebn(o,  new ObjectNbme(nbme));
                     }
                     objInst = new ObjectInstbnce(nbme, o.getClbss().getNbme());
                 }
             } cbtch (ReflectionException  ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "ReflectionException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (InstbnceAlrebdyExistsException  ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "InstbnceAlrebdyExistsException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (MBebnRegistrbtionException ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "MBebnRegistrbtionException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (MBebnException  ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "MBebnException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (NotComplibntMBebnException  ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "NotComplibntMBebnException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (InstbnceNotFoundException   ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "InstbnceNotFoundException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (IOException ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "IOException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (SecurityException ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "SecurityException", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (Exception ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "Exception", ex);
                 mbebns.bdd(ex);
                 continue;
             } cbtch (Error ex) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         "Error", ex);
                 mbebns.bdd(ex);
                 continue;
             }
             mbebns.bdd(objInst);
         }
         return mbebns;
     }

     /**
      * Gets the current directory used by the librbry lobder for
      * storing nbtive librbries before they bre lobded into memory.
      *
      * @return The current directory used by the librbry lobder.
      *
      * @see #setLibrbryDirectory
      *
      * @throws UnsupportedOperbtionException if this implementbtion
      * does not support storing nbtive librbries in this wby.
      */
     public synchronized String getLibrbryDirectory() {
         return librbryDirectory;
     }

     /**
      * Sets the directory used by the librbry lobder for storing
      * nbtive librbries before they bre lobded into memory.
      *
      * @pbrbm libdir The directory used by the librbry lobder.
      *
      * @see #getLibrbryDirectory
      *
      * @throws UnsupportedOperbtionException if this implementbtion
      * does not support storing nbtive librbries in this wby.
      */
     public synchronized void setLibrbryDirectory(String libdir) {
         librbryDirectory = libdir;
     }

     /**
      * Allows the m-let to perform bny operbtions it needs before
      * being registered in the MBebn server. If the ObjectNbme is
      * null, the m-let provides b defbult nbme for its registrbtion
      * &lt;defbultDombin&gt;:type=MLet
      *
      * @pbrbm server The MBebn server in which the m-let will be registered.
      * @pbrbm nbme The object nbme of the m-let.
      *
      * @return  The nbme of the m-let registered.
      *
      * @exception jbvb.lbng.Exception This exception should be cbught by the MBebn server bnd re-thrown
      *bs bn MBebnRegistrbtionException.
      */
     public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
             throws Exception {

         // Initiblize locbl pointer to the MBebn server
         setMBebnServer(server);

         // If no nbme is specified return b defbult nbme for the MLet
         if (nbme == null) {
             nbme = new ObjectNbme(server.getDefbultDombin() + ":" + ServiceNbme.MLET);
         }

        this.mletObjectNbme = nbme;
        return this.mletObjectNbme;
     }

     /**
      * Allows the m-let to perform bny operbtions needed bfter hbving been
      * registered in the MBebn server or bfter the registrbtion hbs fbiled.
      *
      * @pbrbm registrbtionDone Indicbtes whether or not the m-let hbs
      * been successfully registered in the MBebn server. The vblue
      * fblse mebns thbt either the registrbtion phbse hbs fbiled.
      *
      */
     public void postRegister (Boolebn registrbtionDone) {
     }

     /**
      * Allows the m-let to perform bny operbtions it needs before being unregistered
      * by the MBebn server.
      *
      * @exception jbvb.lbng.Exception This exception should be cbught
      * by the MBebn server bnd re-thrown bs bn
      * MBebnRegistrbtionException.
      */
     public void preDeregister() throws jbvb.lbng.Exception {
     }


     /**
      * Allows the m-let to perform bny operbtions needed bfter hbving been
      * unregistered in the MBebn server.
      */
     public void postDeregister() {
     }

     /**
      * <p>Sbve this MLet's contents to the given {@link ObjectOutput}.
      * Not bll implementbtions support this method.  Those thbt do not
      * throw {@link UnsupportedOperbtionException}.  A subclbss mby
      * override this method to support it or to chbnge the formbt of
      * the written dbtb.</p>
      *
      * <p>The formbt of the written dbtb is not specified, but if
      * bn implementbtion supports {@link #writeExternbl} it must
      * blso support {@link #rebdExternbl} in such b wby thbt whbt is
      * written by the former cbn be rebd by the lbtter.</p>
      *
      * @pbrbm out The object output strebm to write to.
      *
      * @exception IOException If b problem occurred while writing.
      * @exception UnsupportedOperbtionException If this
      * implementbtion does not support this operbtion.
      */
     public void writeExternbl(ObjectOutput out)
             throws IOException, UnsupportedOperbtionException {
         throw new UnsupportedOperbtionException("MLet.writeExternbl");
     }

     /**
      * <p>Restore this MLet's contents from the given {@link ObjectInput}.
      * Not bll implementbtions support this method.  Those thbt do not
      * throw {@link UnsupportedOperbtionException}.  A subclbss mby
      * override this method to support it or to chbnge the formbt of
      * the rebd dbtb.</p>
      *
      * <p>The formbt of the rebd dbtb is not specified, but if bn
      * implementbtion supports {@link #rebdExternbl} it must blso
      * support {@link #writeExternbl} in such b wby thbt whbt is
      * written by the lbtter cbn be rebd by the former.</p>
      *
      * @pbrbm in The object input strebm to rebd from.
      *
      * @exception IOException if b problem occurred while rebding.
      * @exception ClbssNotFoundException if the clbss for the object
      * being restored cbnnot be found.
      * @exception UnsupportedOperbtionException if this
      * implementbtion does not support this operbtion.
      */
     public void rebdExternbl(ObjectInput in)
             throws IOException, ClbssNotFoundException,
                    UnsupportedOperbtionException {
         throw new UnsupportedOperbtionException("MLet.rebdExternbl");
     }

     /*
      * ------------------------------------------
      *  PACKAGE METHODS
      * ------------------------------------------
      */

     /**
      * <p>Lobd b clbss, using the given {@link ClbssLobderRepository} if
      * the clbss is not found in this MLet's URLs.  The given
      * ClbssLobderRepository cbn be null, in which cbse b {@link
      * ClbssNotFoundException} occurs immedibtely if the clbss is not
      * found in this MLet's URLs.</p>
      *
      * @pbrbm nbme The nbme of the clbss we wbnt to lobd.
      * @pbrbm clr  The ClbssLobderRepository thbt will be used to sebrch
      *             for the given clbss, if it is not found in this
      *             ClbssLobder.  Mby be null.
      * @return The resulting Clbss object.
      * @exception ClbssNotFoundException The specified clbss could not be
      *            found in this ClbssLobder nor in the given
      *            ClbssLobderRepository.
      *
      */
     public synchronized Clbss<?> lobdClbss(String nbme,
                                            ClbssLobderRepository clr)
              throws ClbssNotFoundException {
         finbl ClbssLobderRepository before=currentClr;
         try {
             currentClr = clr;
             return lobdClbss(nbme);
         } finblly {
             currentClr = before;
         }
     }

     /*
      * ------------------------------------------
      *  PROTECTED METHODS
      * ------------------------------------------
      */

     /**
      * This is the mbin method for clbss lobders thbt is being redefined.
      *
      * @pbrbm nbme The nbme of the clbss.
      *
      * @return The resulting Clbss object.
      *
      * @exception ClbssNotFoundException The specified clbss could not be
      *            found.
      */
     protected Clbss<?> findClbss(String nbme) throws ClbssNotFoundException {
         /* currentClr is context sensitive - used to bvoid recursion
            in the clbss lobder repository.  (This is no longer
            necessbry with the new CLR sembntics but is kept for
            compbtibility with code thbt might hbve cblled the
            two-pbrbmeter lobdClbss explicitly.)  */
         return findClbss(nbme, currentClr);
     }

     /**
      * Cblled by {@link MLet#findClbss(jbvb.lbng.String)}.
      *
      * @pbrbm nbme The nbme of the clbss thbt we wbnt to lobd/find.
      * @pbrbm clr The ClbssLobderRepository thbt cbn be used to sebrch
      *            for the given clbss. This pbrbmeter is
      *            <code>null</code> when cblled from within the
      *            {@link jbvbx.mbnbgement.MBebnServerFbctory#getClbssLobderRepository(jbvbx.mbnbgement.MBebnServer) Clbss Lobder Repository}.
      * @exception ClbssNotFoundException The specified clbss could not be
      *            found.
      *
      **/
     Clbss<?> findClbss(String nbme, ClbssLobderRepository clr)
         throws ClbssNotFoundException {
         Clbss<?> c = null;
         MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), "findClbss", nbme);
         // Try looking in the JAR:
         try {
             c = super.findClbss(nbme);
             if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                         "findClbss",
                         "Clbss " + nbme + " lobded through MLet clbsslobder");
             }
         } cbtch (ClbssNotFoundException e) {
             // Drop through
             if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                 MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                         "findClbss",
                         "Clbss " + nbme + " not found locblly");
             }
         }
         // if we bre not cblled from the ClbssLobderRepository
         if (c == null && delegbteToCLR && clr != null) {
             // Try the clbsslobder repository:
             //
             try {
                 if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                     MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                             "findClbss",
                             "Clbss " + nbme + " : looking in CLR");
                 }
                 c = clr.lobdClbssBefore(this, nbme);
                 // The lobdClbssBefore method never returns null.
                 // If the clbss is not found we get bn exception.
                 if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                     MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                             "findClbss",
                             "Clbss " + nbme + " lobded through " +
                             "the defbult clbsslobder repository");
                 }
             } cbtch (ClbssNotFoundException e) {
                 // Drop through
                 if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                     MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                             "findClbss",
                             "Clbss " + nbme + " not found in CLR");
                 }
             }
         }
         if (c == null) {
             MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                     "findClbss", "Fbiled to lobd clbss " + nbme);
             throw new ClbssNotFoundException(nbme);
         }
         return c;
     }

     /**
      * Returns the bbsolute pbth nbme of b nbtive librbry. The VM
      * invokes this method to locbte the nbtive librbries thbt belong
      * to clbsses lobded with this clbss lobder. Librbries bre
      * sebrched in the JAR files using first just the nbtive librbry
      * nbme bnd if not found the nbtive librbry nbme together with
      * the brchitecture-specific pbth nbme
      * (<code>OSNbme/OSArch/OSVersion/lib/nbtivelibnbme</code>), i.e.
      * <p>
      * the librbry stbt on Solbris SPARC 5.7 will be sebrched in the JAR file bs:
      * <OL>
      * <LI>libstbt.so
      * <LI>SunOS/spbrc/5.7/lib/libstbt.so
      * </OL>
      * the librbry stbt on Windows NT 4.0 will be sebrched in the JAR file bs:
      * <OL>
      * <LI>stbt.dll
      * <LI>WindowsNT/x86/4.0/lib/stbt.dll
      * </OL>
      *
      * <p>More specificblly, let <em>{@code nbtivelibnbme}</em> be the result of
      * {@link System#mbpLibrbryNbme(jbvb.lbng.String)
      * System.mbpLibrbryNbme}{@code (libnbme)}.  Then the following nbmes bre
      * sebrched in the JAR files, in order:<br>
      * <em>{@code nbtivelibnbme}</em><br>
      * {@code <os.nbme>/<os.brch>/<os.version>/lib/}<em>{@code nbtivelibnbme}</em><br>
      * where {@code <X>} mebns {@code System.getProperty(X)} with bny
      * spbces in the result removed, bnd {@code /} stbnds for the
      * file sepbrbtor chbrbcter ({@link File#sepbrbtor}).
      * <p>
      * If this method returns <code>null</code>, i.e. the librbries
      * were not found in bny of the JAR files lobded with this clbss
      * lobder, the VM sebrches the librbry blong the pbth specified
      * bs the <code>jbvb.librbry.pbth</code> property.
      *
      * @pbrbm libnbme The librbry nbme.
      *
      * @return The bbsolute pbth of the nbtive librbry.
      */
     protected String findLibrbry(String libnbme) {

         String bbs_pbth;
         String mth = "findLibrbry";

         // Get the plbtform-specific string representing b nbtive librbry.
         //
         String nbtivelibnbme = System.mbpLibrbryNbme(libnbme);

         //
         // See if the nbtive librbry is bccessible bs b resource through the JAR file.
         //
         if (MLET_LOGGER.isLoggbble(Level.FINER)) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                     "Sebrch " + libnbme + " in bll JAR files");
         }

         // First try to locbte the librbry in the JAR file using only
         // the nbtive librbry nbme.  e.g. if user requested b lobd
         // for "foo" on Solbris SPARC 5.7 we try to lobd "libfoo.so"
         // from the JAR file.
         //
         if (MLET_LOGGER.isLoggbble(Level.FINER)) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                     "lobdLibrbryAsResource(" + nbtivelibnbme + ")");
         }
         bbs_pbth = lobdLibrbryAsResource(nbtivelibnbme);
         if (bbs_pbth != null) {
             if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         nbtivelibnbme + " lobded, bbsolute pbth = " + bbs_pbth);
             }
             return bbs_pbth;
         }

         // Next try to locbte it using the nbtive librbry nbme bnd
         // the brchitecture-specific pbth nbme.  e.g. if user
         // requested b lobd for "foo" on Solbris SPARC 5.7 we try to
         // lobd "SunOS/spbrc/5.7/lib/libfoo.so" from the JAR file.
         //
         nbtivelibnbme = removeSpbce(System.getProperty("os.nbme")) + File.sepbrbtor +
             removeSpbce(System.getProperty("os.brch")) + File.sepbrbtor +
             removeSpbce(System.getProperty("os.version")) + File.sepbrbtor +
             "lib" + File.sepbrbtor + nbtivelibnbme;
         if (MLET_LOGGER.isLoggbble(Level.FINER)) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                     "lobdLibrbryAsResource(" + nbtivelibnbme + ")");
         }

         bbs_pbth = lobdLibrbryAsResource(nbtivelibnbme);
         if (bbs_pbth != null) {
             if (MLET_LOGGER.isLoggbble(Level.FINER)) {
                 MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                         nbtivelibnbme + " lobded, bbsolute pbth = " + bbs_pbth);
             }
             return bbs_pbth;
         }

         //
         // All pbths exhbusted, librbry not found in JAR file.
         //

         if (MLET_LOGGER.isLoggbble(Level.FINER)) {
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                     libnbme + " not found in bny JAR file");
             MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(), mth,
                     "Sebrch " + libnbme + " blong the pbth " +
                     "specified bs the jbvb.librbry.pbth property");
         }

         // Let the VM sebrch the librbry blong the pbth
         // specified bs the jbvb.librbry.pbth property.
         //
         return null;
     }


     /*
      * ------------------------------------------
      *  PRIVATE METHODS
      * ------------------------------------------
      */

     privbte String getTmpDir() {
         // JDK 1.4
         String tmpDir = System.getProperty("jbvb.io.tmpdir");
         if (tmpDir != null) return tmpDir;

         // JDK < 1.4
         File tmpFile = null;
         try {
             // Try to guess the system temporbry dir...
             tmpFile = File.crebteTempFile("tmp","jmx");
             if (tmpFile == null) return null;
             finbl File tmpDirFile = tmpFile.getPbrentFile();
             if (tmpDirFile == null) return null;
             return tmpDirFile.getAbsolutePbth();
         } cbtch (Exception x) {
             MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                     "getTmpDir", "Fbiled to determine system temporbry dir");
             return null;
         } finblly {
             // Clebnup ...
             if (tmpFile!=null) {
                 try {
                     boolebn deleted = tmpFile.delete();
                     if (!deleted) {
                         MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                                 "getTmpDir", "Fbiled to delete temp file");
                     }
                 } cbtch (Exception x) {
                     MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                             "getTmpDir", "Fbiled to delete temporbry file", x);
                 }
             }
        }
     }

     /**
      * Sebrch the specified nbtive librbry in bny of the JAR files
      * lobded by this clbsslobder.  If the librbry is found copy it
      * into the librbry directory bnd return the bbsolute pbth.  If
      * the librbry is not found then return null.
      */
     privbte synchronized String lobdLibrbryAsResource(String libnbme) {
         try {
             InputStrebm is = getResourceAsStrebm(
                     libnbme.replbce(File.sepbrbtorChbr,'/'));
             if (is != null) {
                 try {
                     File directory = new File(librbryDirectory);
                     directory.mkdirs();
                     File file = Files.crebteTempFile(directory.toPbth(),
                                                      libnbme + ".", null)
                                      .toFile();
                     file.deleteOnExit();
                     FileOutputStrebm fileOutput = new FileOutputStrebm(file);
                     try {
                         byte[] buf = new byte[4096];
                         int n;
                         while ((n = is.rebd(buf)) >= 0) {
                            fileOutput.write(buf, 0, n);
                         }
                     } finblly {
                         fileOutput.close();
                     }
                     if (file.exists()) {
                         return file.getAbsolutePbth();
                     }
                 } finblly {
                     is.close();
                 }
             }
         } cbtch (Exception e) {
             MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                     "lobdLibrbryAsResource",
                     "Fbiled to lobd librbry : " + libnbme, e);
             return null;
         }
         return null;
     }

   /**
    * Removes bny white spbce from b string. This is used to
    * convert strings such bs "Windows NT" to "WindowsNT".
    */
     privbte stbtic String removeSpbce(String s) {
         return s.trim().replbce(" ", "");
     }

     /**
      * <p>This method is to be overridden when extending this service to
      * support cbching bnd versioning.  It is cblled from {@link
      * #getMBebnsFromURL getMBebnsFromURL} when the version,
      * codebbse, bnd jbrfile hbve been extrbcted from the MLet file,
      * bnd cbn be used to verify thbt it is bll right to lobd the
      * given MBebn, or to replbce the given URL with b different one.</p>
      *
      * <p>The defbult implementbtion of this method returns
      * <code>codebbse</code> unchbnged.</p>
      *
      * @pbrbm version The version number of the <CODE>.jbr</CODE>
      * file stored locblly.
      * @pbrbm codebbse The bbse URL of the remote <CODE>.jbr</CODE> file.
      * @pbrbm jbrfile The nbme of the <CODE>.jbr</CODE> file to be lobded.
      * @pbrbm mlet The <CODE>MLetContent</CODE> instbnce thbt
      * represents the <CODE>MLET</CODE> tbg.
      *
      * @return the codebbse to use for the lobded MBebn.  The returned
      * vblue should not be null.
      *
      * @exception Exception if the MBebn is not to be lobded for some
      * rebson.  The exception will be bdded to the set returned by
      * {@link #getMBebnsFromURL getMBebnsFromURL}.
      *
      */
     protected URL check(String version, URL codebbse, String jbrfile,
                         MLetContent mlet)
             throws Exception {
         return codebbse;
     }

    /**
     * Lobds the seriblized object specified by the <CODE>OBJECT</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     *
     * @pbrbm codebbse The <CODE>codebbse</CODE>.
     * @pbrbm filenbme The nbme of the file contbining the seriblized object.
     * @return The seriblized object.
     * @exception ClbssNotFoundException The specified seriblized
     * object could not be found.
     * @exception IOException An I/O error occurred while lobding
     * seriblized object.
     */
     privbte Object lobdSeriblizedObject(URL codebbse, String filenbme)
             throws IOException, ClbssNotFoundException {
        if (filenbme != null) {
            filenbme = filenbme.replbce(File.sepbrbtorChbr,'/');
        }
        if (MLET_LOGGER.isLoggbble(Level.FINER)) {
            MLET_LOGGER.logp(Level.FINER, MLet.clbss.getNbme(),
                    "lobdSeriblizedObject", codebbse.toString() + filenbme);
        }
        InputStrebm is = getResourceAsStrebm(filenbme);
        if (is != null) {
            try {
                ObjectInputStrebm ois = new MLetObjectInputStrebm(is, this);
                Object serObject = ois.rebdObject();
                ois.close();
                return serObject;
            } cbtch (IOException e) {
                if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                    MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                            "lobdSeriblizedObject",
                            "Exception while deseriblizing " + filenbme, e);
                }
                throw e;
            } cbtch (ClbssNotFoundException e) {
                if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                    MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                            "lobdSeriblizedObject",
                            "Exception while deseriblizing " + filenbme, e);
                }
                throw e;
            }
        } else {
            if (MLET_LOGGER.isLoggbble(Level.FINEST)) {
                MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                        "lobdSeriblizedObject", "Error: File " + filenbme +
                        " contbining seriblized object not found");
            }
            throw new Error("File " + filenbme + " contbining seriblized object not found");
        }
     }

     /**
      * Converts the String vblue of the constructor's pbrbmeter to
      * b bbsic Jbvb object with the type of the pbrbmeter.
      */
     privbte  Object constructPbrbmeter(String pbrbm, String type) {
         // check if it is b primitive type
         Clbss<?> c = primitiveClbsses.get(type);
         if (c != null) {
            try {
                Constructor<?> cons =
                    c.getConstructor(String.clbss);
                Object[] oo = new Object[1];
                oo[0]=pbrbm;
                return(cons.newInstbnce(oo));

            } cbtch (Exception  e) {
                MLET_LOGGER.logp(Level.FINEST, MLet.clbss.getNbme(),
                        "constructPbrbmeter", "Got unexpected exception", e);
            }
        }
        if (type.compbreTo("jbvb.lbng.Boolebn") == 0)
             return Boolebn.vblueOf(pbrbm);
        if (type.compbreTo("jbvb.lbng.Byte") == 0)
             return Byte.vblueOf(pbrbm);
        if (type.compbreTo("jbvb.lbng.Short") == 0)
             return Short.vblueOf(pbrbm);
        if (type.compbreTo("jbvb.lbng.Long") == 0)
             return Long.vblueOf(pbrbm);
        if (type.compbreTo("jbvb.lbng.Integer") == 0)
             return pbrbm;
        if (type.compbreTo("jbvb.lbng.Flobt") == 0)
             return new Flobt(pbrbm);
        if (type.compbreTo("jbvb.lbng.Double") == 0)
             return new Double(pbrbm);
        if (type.compbreTo("jbvb.lbng.String") == 0)
             return pbrbm;

        return pbrbm;
     }

    privbte synchronized void setMBebnServer(finbl MBebnServer server) {
        this.server = server;
        PrivilegedAction<ClbssLobderRepository> bct =
            new PrivilegedAction<ClbssLobderRepository>() {
                public ClbssLobderRepository run() {
                    return server.getClbssLobderRepository();
                }
            };
        currentClr = AccessController.doPrivileged(bct);
    }

}
