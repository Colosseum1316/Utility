package colosseum.utility.unittest;

import colosseum.utility.GameTypeInfo;
import colosseum.utility.arcade.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TestGameType {
    @Test
    void testGameType() throws Exception {
        String sysProperty = System.getProperty("mapdata.xsd");
        Assumptions.assumeTrue(sysProperty != null);
        File file = new File(sysProperty);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                if ("xs".equals(prefix)) {
                    return "http://www.w3.org/2001/XMLSchema";
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public Iterator<String> getPrefixes(String namespaceURI) {
                return null;
            }
        });

        String expression = "//xs:simpleType[@name='gameTypeEnum']//xs:enumeration/@value";
        NodeList enumNodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
        Assertions.assertEquals(GameType.getEntries().size(), enumNodes.getLength());
        Set<GameType> pending = GameType.getEntries().stream().collect(Collectors.toSet());
        for (int i = 0; i < enumNodes.getLength(); i++) {
            String value = enumNodes.item(i).getNodeValue();
            GameType type = GameType.valueOf(value);
            Assertions.assertEquals(type.name(), value);
            Assertions.assertTrue(pending.contains(type));
            pending.remove(type);
        }
    }

    @Test
    void testAddRemoveInfo() {
        GameType.getEntries().forEach(v -> {
            List<String> info = new ArrayList<>();
            GameTypeInfo gameTypeInfo = new GameTypeInfo(v, info);
            gameTypeInfo.addInfo("1");
            gameTypeInfo.addInfo("2");
            gameTypeInfo.addInfo("3");
            Assertions.assertLinesMatch(Arrays.asList("1", "2", "3"), info);
            gameTypeInfo.removeInfo(0);
            Assertions.assertLinesMatch(Arrays.asList("2", "3"), info);
            gameTypeInfo.removeInfo(1);
            Assertions.assertLinesMatch(Arrays.asList("2"), info);
        });
    }
}
