package com.microservices.services.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public class PageDeserializer extends StdDeserializer<Page<?>> {

    public PageDeserializer() {
        super((Class<Page<?>>) null);
    }

    @Override
    public Page<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        JsonNode contentNode = node.get("content");

        List<?> content = codec.treeToValue(contentNode, List.class);
        int page = node.get("number").asInt();
        int size = node.get("size").asInt();
        long total = node.get("totalElements").asLong();

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, total);
    }
}

