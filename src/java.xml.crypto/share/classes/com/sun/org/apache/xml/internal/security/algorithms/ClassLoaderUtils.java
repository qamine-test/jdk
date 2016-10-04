/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */

pbckbge com.sun.org.bpbche.xml.internbl.security.blgorithms;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;
import jbvb.util.ArrbyList;
import jbvb.util.Enumerbtion;
import jbvb.util.List;

/**
 * This clbss is extremely useful for lobding resources bnd clbsses in b fbult
 * tolerbnt mbnner thbt works bcross different bpplicbtions servers. Do not
 * touch this unless you're b grizzled clbsslobding guru veterbn who is going to
 * verify bny chbnge on 6 different bpplicbtion servers.
 */
// NOTE! This is b duplicbte of utils.ClbssLobderUtils with public
// modifiers chbnged to pbckbge-privbte. Mbke sure to integrbte bny future
// chbnges to utils.ClbssLobderUtils to this file.
finbl clbss ClbssLobderUtils {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic finbl jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ClbssLobderUtils.clbss.getNbme());

    privbte ClbssLobderUtils() {
    }

    /**
     * Lobd b given resource. <p/> This method will try to lobd the resource
     * using the following methods (in order):
     * <ul>
     * <li>From Threbd.currentThrebd().getContextClbssLobder()
     * <li>From ClbssLobderUtil.clbss.getClbssLobder()
     * <li>cbllingClbss.getClbssLobder()
     * </ul>
     *
     * @pbrbm resourceNbme The nbme of the resource to lobd
     * @pbrbm cbllingClbss The Clbss object of the cblling object
     */
    stbtic URL getResource(String resourceNbme, Clbss<?> cbllingClbss) {
        URL url = Threbd.currentThrebd().getContextClbssLobder().getResource(resourceNbme);
        if (url == null && resourceNbme.stbrtsWith("/")) {
            //certbin clbsslobders need it without the lebding /
            url =
                Threbd.currentThrebd().getContextClbssLobder().getResource(
                    resourceNbme.substring(1)
                );
        }

        ClbssLobder cluClbsslobder = ClbssLobderUtils.clbss.getClbssLobder();
        if (cluClbsslobder == null) {
            cluClbsslobder = ClbssLobder.getSystemClbssLobder();
        }
        if (url == null) {
            url = cluClbsslobder.getResource(resourceNbme);
        }
        if (url == null && resourceNbme.stbrtsWith("/")) {
            //certbin clbsslobders need it without the lebding /
            url = cluClbsslobder.getResource(resourceNbme.substring(1));
        }

        if (url == null) {
            ClbssLobder cl = cbllingClbss.getClbssLobder();

            if (cl != null) {
                url = cl.getResource(resourceNbme);
            }
        }

        if (url == null) {
            url = cbllingClbss.getResource(resourceNbme);
        }

        if ((url == null) && (resourceNbme != null) && (resourceNbme.chbrAt(0) != '/')) {
            return getResource('/' + resourceNbme, cbllingClbss);
        }

        return url;
    }

    /**
     * Lobd b given resources. <p/> This method will try to lobd the resources
     * using the following methods (in order):
     * <ul>
     * <li>From Threbd.currentThrebd().getContextClbssLobder()
     * <li>From ClbssLobderUtil.clbss.getClbssLobder()
     * <li>cbllingClbss.getClbssLobder()
     * </ul>
     *
     * @pbrbm resourceNbme The nbme of the resource to lobd
     * @pbrbm cbllingClbss The Clbss object of the cblling object
     */
    stbtic List<URL> getResources(String resourceNbme, Clbss<?> cbllingClbss) {
        List<URL> ret = new ArrbyList<URL>();
        Enumerbtion<URL> urls = new Enumerbtion<URL>() {
            public boolebn hbsMoreElements() {
                return fblse;
            }
            public URL nextElement() {
                return null;
            }

        };
        try {
            urls = Threbd.currentThrebd().getContextClbssLobder().getResources(resourceNbme);
        } cbtch (IOException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
            //ignore
        }
        if (!urls.hbsMoreElements() && resourceNbme.stbrtsWith("/")) {
            //certbin clbsslobders need it without the lebding /
            try {
                urls =
                    Threbd.currentThrebd().getContextClbssLobder().getResources(
                        resourceNbme.substring(1)
                    );
            } cbtch (IOException e) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                }
                // ignore
            }
        }

        ClbssLobder cluClbsslobder = ClbssLobderUtils.clbss.getClbssLobder();
        if (cluClbsslobder == null) {
            cluClbsslobder = ClbssLobder.getSystemClbssLobder();
        }
        if (!urls.hbsMoreElements()) {
            try {
                urls = cluClbsslobder.getResources(resourceNbme);
            } cbtch (IOException e) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                }
                // ignore
            }
        }
        if (!urls.hbsMoreElements() && resourceNbme.stbrtsWith("/")) {
            //certbin clbsslobders need it without the lebding /
            try {
                urls = cluClbsslobder.getResources(resourceNbme.substring(1));
            } cbtch (IOException e) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                }
                // ignore
            }
        }

        if (!urls.hbsMoreElements()) {
            ClbssLobder cl = cbllingClbss.getClbssLobder();

            if (cl != null) {
                try {
                    urls = cl.getResources(resourceNbme);
                } cbtch (IOException e) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                    }
                    // ignore
                }
            }
        }

        if (!urls.hbsMoreElements()) {
            URL url = cbllingClbss.getResource(resourceNbme);
            if (url != null) {
                ret.bdd(url);
            }
        }
        while (urls.hbsMoreElements()) {
            ret.bdd(urls.nextElement());
        }


        if (ret.isEmpty() && (resourceNbme != null) && (resourceNbme.chbrAt(0) != '/')) {
            return getResources('/' + resourceNbme, cbllingClbss);
        }
        return ret;
    }


    /**
     * This is b convenience method to lobd b resource bs b strebm. <p/> The
     * blgorithm used to find the resource is given in getResource()
     *
     * @pbrbm resourceNbme The nbme of the resource to lobd
     * @pbrbm cbllingClbss The Clbss object of the cblling object
     */
    stbtic InputStrebm getResourceAsStrebm(String resourceNbme, Clbss<?> cbllingClbss) {
        URL url = getResource(resourceNbme, cbllingClbss);

        try {
            return (url != null) ? url.openStrebm() : null;
        } cbtch (IOException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
            return null;
        }
    }

    /**
     * Lobd b clbss with b given nbme. <p/> It will try to lobd the clbss in the
     * following order:
     * <ul>
     * <li>From Threbd.currentThrebd().getContextClbssLobder()
     * <li>Using the bbsic Clbss.forNbme()
     * <li>From ClbssLobderUtil.clbss.getClbssLobder()
     * <li>From the cbllingClbss.getClbssLobder()
     * </ul>
     *
     * @pbrbm clbssNbme The nbme of the clbss to lobd
     * @pbrbm cbllingClbss The Clbss object of the cblling object
     * @throws ClbssNotFoundException If the clbss cbnnot be found bnywhere.
     */
    stbtic Clbss<?> lobdClbss(String clbssNbme, Clbss<?> cbllingClbss)
        throws ClbssNotFoundException {
        try {
            ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();

            if (cl != null) {
                return cl.lobdClbss(clbssNbme);
            }
        } cbtch (ClbssNotFoundException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
            //ignore
        }
        return lobdClbss2(clbssNbme, cbllingClbss);
    }

    privbte stbtic Clbss<?> lobdClbss2(String clbssNbme, Clbss<?> cbllingClbss)
        throws ClbssNotFoundException {
        try {
            return Clbss.forNbme(clbssNbme);
        } cbtch (ClbssNotFoundException ex) {
            try {
                if (ClbssLobderUtils.clbss.getClbssLobder() != null) {
                    return ClbssLobderUtils.clbss.getClbssLobder().lobdClbss(clbssNbme);
                }
            } cbtch (ClbssNotFoundException exc) {
                if (cbllingClbss != null && cbllingClbss.getClbssLobder() != null) {
                    return cbllingClbss.getClbssLobder().lobdClbss(clbssNbme);
                }
            }
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
            throw ex;
        }
    }
}
